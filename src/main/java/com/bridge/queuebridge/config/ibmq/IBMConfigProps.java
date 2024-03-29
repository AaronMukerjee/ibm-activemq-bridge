package com.bridge.queuebridge.config.ibmq;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ibm.mq")
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class IBMConfigProps {
    String host;
    String user;
    String password;
    String queueManager;
    Integer port;
    String channel;
    Integer transportType;
}
