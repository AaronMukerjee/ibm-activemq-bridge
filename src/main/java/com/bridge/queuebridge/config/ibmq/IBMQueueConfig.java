package com.bridge.queuebridge.config.ibmq;

import com.bridge.queuebridge.config.activemq.ActiveMqConfigProps;
import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import com.ibm.mq.spring.boot.MQConfigurationProperties;
import com.ibm.mq.spring.boot.MQConnectionFactoryFactory;
import jakarta.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class IBMQueueConfig {
    @Bean("ibmQueueJmsTemplate")
    JmsTemplate ibmQueueJmsTemplate(IBMConfigProps ibmConfigProps){
        MQConnectionFactory  mqConnectionFactory = new MQConnectionFactory();
        mqConnectionFactory.setHostName(ibmConfigProps.getHost());
        JmsTemplate jmsTemplate = new JmsTemplate(mqConnectionFactory);
        return jmsTemplate;
    }
    @Bean("ibmQListenerContainerFactory")
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(IBMConfigProps ibmConfigProps) throws JMSException {
        MQConnectionFactory  mqConnectionFactory = new MQConnectionFactory();
        mqConnectionFactory.setHostName(ibmConfigProps.getHost());
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(mqConnectionFactory);
        factory.setConcurrency("1-1"); // Set concurrency (optional)
        return factory;
    }
}
