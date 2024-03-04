package com.bridge.queuebridge.config.activemq;

import jakarta.jms.JMSException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ActiveMQConfg {
    @Bean("activeMQTemplate")
    public JmsTemplate jmsTemplate(ActiveMqConfigProps activeMqConfigProps){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(activeMqConfigProps.getBrokerUrl()); // Replace with your broker URL
        connectionFactory.setPassword(activeMqConfigProps.getPassword()); // Replace with your password
        connectionFactory.setUserName(activeMqConfigProps.getUser()); // Replace with your username
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        return jmsTemplate;
    }

    @Bean("activeMqListenerContainerFactory")
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ActiveMqConfigProps activeMqConfigProps) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(activeMqConfigProps.getBrokerUrl()); // Replace with your broker URL
        activeMQConnectionFactory.setPassword(activeMqConfigProps.getPassword()); // Replace with your password
        activeMQConnectionFactory.setUserName(activeMqConfigProps.getUser()); // Replace with your username
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        factory.setConcurrency("1-1"); // Set concurrency (optional)
        return factory;
    }
}
