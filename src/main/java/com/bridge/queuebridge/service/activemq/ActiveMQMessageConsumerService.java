package com.bridge.queuebridge.service.activemq;

import com.bridge.queuebridge.service.ibmq.IBMQMessageProducerService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActiveMQMessageConsumerService {
    @Autowired
    @Qualifier("activeMQTemplate")
    private JmsTemplate jmsTemplate;
    @Autowired
    IBMQMessageProducerService producerService;
    @JmsListener(destination = "${amq.queue.out.name}",containerFactory = "activeMqListenerContainerFactory")
    public void receiveMessage(Message message) throws JMSException {
        System.out.println("Received message: " + message.getBody(String.class));
        producerService.sendMessage(message.getBody(String.class));
    }
}
