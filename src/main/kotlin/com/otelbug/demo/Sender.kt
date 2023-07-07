package com.otelbug.demo

import com.otelbug.demo.AppConfiguration.Companion.QUEUE_NAME
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import java.util.UUID

@Component
class Sender(
    val sqsClient: SqsAsyncClient,
    @Qualifier(QUEUE_NAME) val sqsQueueUrl: String,
) {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(Sender::class.java)
    }

    @Scheduled(fixedRate = 5000)
    fun sendJob() {
        sendRandomMessage()
        LOGGER.info("...sent")
    }

    fun sendRandomMessage() {
        sqsClient.sendMessage(
            SendMessageRequest.builder().messageBody(
                """
                {"some_random_uuid": "${UUID.randomUUID()}"}
                """.trimIndent(),
            ).queueUrl(sqsQueueUrl).build(),
        ).get()
    }
}
