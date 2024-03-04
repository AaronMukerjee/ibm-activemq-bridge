package com.bridge.queuebridge.service.ibmq;

import com.bridge.queuebridge.service.activemq.ActiveMQMessageProducerService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IBMQMessageConsumerService {
    @Autowired
    ActiveMQMessageProducerService  producerService;
    @JmsListener(destination = "${ibm.queue.in.name}",containerFactory = "ibmQListenerContainerFactory")
    public void receiveMessage(Message message) throws JMSException {
        System.out.println("Received message: " + message.getBody(String.class));
        producerService.sendMessage(message.getBody(String.class));
    }
}
