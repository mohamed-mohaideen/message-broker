package com.msoft.mq.service;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

import com.msoft.mq.data.Message;

@Service
public class SimpleBroker {

	private ConcurrentHashMap<String, BlockingQueue<Message>> topics = new ConcurrentHashMap<>();
	
	public void publish(String topic, String payload) {
		topics.computeIfAbsent(topic, k -> new LinkedBlockingQueue<>())
		.offer(new Message(topic, payload, LocalDateTime.now()));
	}
	
	public Message subscribe(String topic) throws InterruptedException {
		return topics.computeIfAbsent(topic, k -> new LinkedBlockingQueue<>())
		.take();
	}
}
