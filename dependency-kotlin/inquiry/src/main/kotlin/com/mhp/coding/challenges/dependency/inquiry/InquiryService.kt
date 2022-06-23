package com.mhp.coding.challenges.dependency.inquiry

import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.connection.CachingConnectionFactory
import org.springframework.jms.connection.SingleConnectionFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import org.springframework.stereotype.Component
import javax.jms.ConnectionFactory
import javax.jms.Queue


private val logger = KotlinLogging.logger {}

@Component
class InquiryService() {
    val jmsTemplate = JmsTemplate();
    fun create(inquiry: Inquiry) {
        logger.info {
            "User sent inquiry: $inquiry"
        }
        val connectionFactory = SingleConnectionFactory()
        connectionFactory.targetConnectionFactory = CachingConnectionFactory()
        jmsTemplate.connectionFactory = connectionFactory

        jmsTemplate.convertAndSend("test", "test")
    }

}

data class Inquiry(
    var username: String,
    var recipient: String,
    var text: String,
)


@Configuration
class JMSConfig {
    @Bean
    fun containerFactory(): DefaultJmsListenerContainerFactory {
        val connectionFactory = SingleConnectionFactory()
        connectionFactory.targetConnectionFactory = CachingConnectionFactory()
        val factory = DefaultJmsListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setSessionTransacted(true)
        factory.setMaxMessagesPerTask(2)
        factory.setConcurrency("1-5")
        return factory
    }

}
