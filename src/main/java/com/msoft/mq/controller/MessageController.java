package com.msoft.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msoft.mq.data.Message;
import com.msoft.mq.service.SimpleBroker;

@RestController
@RequestMapping("/broker")
public class MessageController {

	@Autowired private SimpleBroker broker;
	
    @PostMapping("/publish/{topic}")
    public String publish(@PathVariable String topic, @RequestBody String payload) {
        broker.publish(topic, payload);
        return "Message published to " + topic;
    }

    @GetMapping("/consume/{topic}")
    public Message consume(@PathVariable String topic) throws InterruptedException {
        return broker.subscribe(topic);
    }
}
