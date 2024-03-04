package com.bridge.queuebridge;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.util.Properties;

@SpringBootApplication(exclude = {ActiveMQAutoConfiguration.class, JmsAutoConfiguration.class})
@EnableJms
public class QueueBridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueueBridgeApplication.class, args);
	}

}
