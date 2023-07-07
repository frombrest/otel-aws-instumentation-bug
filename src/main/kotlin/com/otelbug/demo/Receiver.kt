package com.otelbug.demo

import com.otelbug.demo.AppConfiguration.Companion.QUEUE_NAME
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest

@Component
class Receiver(
    val sqsClient: SqsAsyncClient,
    @Qualifier(QUEUE_NAME) val sqsQueueUrl: String,
) {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(Receiver::class.java)
    }

    @Scheduled(fixedRate = 3000)
    fun receiveJob() {
        val messageList = receiveMessages()
        if (!messageList.isEmpty()) {
            LOGGER.info("Received: ${messageList[0].body()}")
        } else {
            LOGGER.info("Received 0 messages")
        }
    }

    fun receiveMessages(): List<Message> {
        return sqsClient.receiveMessage(ReceiveMessageRequest.builder().queueUrl(sqsQueueUrl).maxNumberOfMessages(1).waitTimeSeconds(3).build()).get().messages()
    }
}
