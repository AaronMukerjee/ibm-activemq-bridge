package com.bridge.queuebridge.service.ibmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class IBMQMessageProducerService {
    @Autowired
    @Qualifier("ibmQueueJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Value("${ibm.queue.out.name}")
    String destinationQueue;

    public void sendMessage(String message) {
        this.jmsTemplate.convertAndSend(destinationQueue, message);
    }
}
