package com.bridge.queuebridge.config.ibmq;

import com.bridge.queuebridge.config.activemq.ActiveMqConfigProps;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import com.ibm.mq.spring.boot.MQConfigurationProperties;
import com.ibm.mq.spring.boot.MQConnectionFactoryFactory;
import com.ibm.msg.client.jakarta.wmq.WMQConstants;
import jakarta.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
        mqConnectionFactory.setQueueManager(ibmConfigProps.getQueueManager());
        mqConnectionFactory.setPort(ibmConfigProps.getPort() == null ? 1414 : ibmConfigProps.getPort());
        mqConnectionFactory.setChannel(ibmConfigProps.getChannel());
        mqConnectionFactory.setTransportType(
                ibmConfigProps.getTransportType() == null
                        ? WMQConstants.WMQ_CM_CLIENT
                        : ibmConfigProps.getTransportType());
        mqConnectionFactory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
        mqConnectionFactory.setStringProperty(WMQConstants.USERID, ibmConfigProps.getUser());
        mqConnectionFactory.setStringProperty(WMQConstants.PASSWORD, ibmConfigProps.getPassword());
        mqConnectionFactory.setStringProperty(CMQC.USER_ID_PROPERTY, ibmConfigProps.getUser());
        mqConnectionFactory.setStringProperty(CMQC.PASSWORD_PROPERTY, ibmConfigProps.getPassword());
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(mqConnectionFactory);
        factory.setConcurrency("1-1");
        return factory;
    }
}
