package com.mhp.coding.challenges.dependency.notifications

import com.mhp.coding.challenges.dependency.inquiry.Inquiry
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component


private val logger = KotlinLogging.logger {}

@Component
class EmailHandler {
    @JmsListener(destination = "test", containerFactory = "containerFactory")
    fun sendEmail(inquiry: Inquiry) {
        logger.info {
            "Sending email for: $inquiry"
        }
    }


}
