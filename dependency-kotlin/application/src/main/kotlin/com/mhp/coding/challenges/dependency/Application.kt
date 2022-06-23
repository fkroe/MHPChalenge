package com.mhp.coding.challenges.dependency

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.core.JmsTemplate


@SpringBootApplication
@EnableJms
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}