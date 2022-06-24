package com.mhp.coding.challenges.dependency.notifications

import com.mhp.coding.challenges.dependency.inquiry.Inquiry
import mu.KotlinLogging
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


private val logger = KotlinLogging.logger {}

@Component
class PushNotificationHandler {
    @RabbitListener(queues = ["myqueue"])
    fun sendNotification(inquiry: Inquiry) {

        logger.info {
            "Sending push notification for: $inquiry"
        }
    }
}