package com.msoft.mq.data;

import java.time.LocalDateTime;

public record Message(String topic, String payload, LocalDateTime timestamp) {

}
