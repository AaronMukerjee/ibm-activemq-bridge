package com.bridge.queuebridge;

import com.bridge.queuebridge.service.activemq.ActiveMQMessageProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    final ActiveMQMessageProducerService producerService;
    @PostMapping("/send")
    public void sendMessage(@RequestBody String message){
        producerService.sendMessage(message);
    }
}
