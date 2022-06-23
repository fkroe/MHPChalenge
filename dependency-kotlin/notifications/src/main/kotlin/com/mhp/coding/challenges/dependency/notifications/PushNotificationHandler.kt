package com.mhp.coding.challenges.dependency.notifications

import com.mhp.coding.challenges.dependency.inquiry.Inquiry
import mu.KotlinLogging
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component


private val logger = KotlinLogging.logger {}

@Component
class PushNotificationHandler {
    //@JmsListener(destination = "test", containerFactory = "containerFactory")
    fun sendNotification(inquiry: Inquiry) {
        logger.info {
            "Sending push notification for: $inquiry"
        }
    }
}