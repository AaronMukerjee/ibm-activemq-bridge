package com.bridge.queuebridge.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Message {
    String message;
}
