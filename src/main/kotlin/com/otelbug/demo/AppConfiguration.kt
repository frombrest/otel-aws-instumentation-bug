package com.otelbug.demo

import io.opentelemetry.instrumentation.awssdk.v2_2.autoconfigure.TracingExecutionInterceptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest

@Configuration
class AppConfiguration {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(AppConfiguration::class.java)
        const val QUEUE_NAME = "test-queue-name"
    }

    @Bean
    fun container(): LocalStackContainer {
        val localstack = LocalStackContainer(DockerImageName.parse("localstack/localstack:1.4.0"))
        localstack.withServices(LocalStackContainer.Service.SQS)
        localstack.start()
        return localstack
    }

    @Bean
    fun sqsClient(container: LocalStackContainer): SqsAsyncClient {
        val httpClient = NettyNioAsyncHttpClient.builder().build()
        return SqsAsyncClient.builder()
            .httpClient(httpClient)
            .endpointOverride(container.endpoint)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(container.accessKey, container.secretKey),
                ),
            )
            .region(Region.of(container.region))
            .overrideConfiguration(getClientOverrideConfiguration()).build()
    }

    private fun getClientOverrideConfiguration(): ClientOverrideConfiguration {
        return ClientOverrideConfiguration.builder()
            .addExecutionInterceptor(TracingExecutionInterceptor())
            .build()
    }

    @Bean(QUEUE_NAME)
    fun queueURL(sqsClient: SqsAsyncClient): String {
        LOGGER.info("Creating queue")
        val result = sqsClient.createQueue(CreateQueueRequest.builder().queueName(QUEUE_NAME).build()).get()
        LOGGER.info("Queue {} created", result.queueUrl())
        return result.queueUrl()
    }
}
