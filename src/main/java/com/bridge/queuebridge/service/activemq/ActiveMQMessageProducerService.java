package com.bridge.queuebridge.service.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQMessageProducerService {
    @Autowired
    @Qualifier("activeMQTemplate")
    private JmsTemplate jmsTemplate;
    @Value(("amq.queue.in.name"))
    String destinationName;

    public void sendMessage(String message) {
        this.jmsTemplate.convertAndSend(destinationName, message);
    }
}
