package com.bridge.queuebridge.config.activemq;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.activemq")
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ActiveMqConfigProps {
    String user;
    String password;
    String brokerUrl;
}
