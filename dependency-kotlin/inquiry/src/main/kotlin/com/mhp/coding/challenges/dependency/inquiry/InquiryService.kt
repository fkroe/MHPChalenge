package com.mhp.coding.challenges.dependency.inquiry

import mu.KotlinLogging
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component


private val logger = KotlinLogging.logger {}

@Component
class InquiryService() {
    fun create(inquiry: Inquiry) {
        logger.info {
            "User sent inquiry: $inquiry"
        }

        val context: ApplicationContext = AnnotationConfigApplicationContext(RabbitConfiguration::class.java)
        val template = context.getBean(AmqpTemplate::class.java)
        template.convertAndSend("queue", inquiry)
        template.convertAndSend("queue2", inquiry)
    }

}
data class Inquiry(
    var username: String,
    var recipient: String,
    var text: String,
) :java.io.Serializable

@Configuration
class RabbitConfiguration {
    @Bean
    fun connectionFactory(): CachingConnectionFactory {
        return CachingConnectionFactory("localhost")
    }

    @Bean
    fun amqpAdmin(): RabbitAdmin {
        return RabbitAdmin(connectionFactory())
    }

    @Bean
    fun rabbitTemplate(): RabbitTemplate {
        return RabbitTemplate(connectionFactory())
    }

    @Bean
    fun queue(): Queue {
        return Queue("queue",false)
    }
    @Bean
    fun queue2(): Queue {
        return Queue("queue2", false)
    }

}

