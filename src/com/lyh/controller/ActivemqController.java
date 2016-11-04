package com.lyh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyh.mq.producer.queue.QueueSender;
import com.lyh.mq.producer.topic.TopicSender;

@Controller
@RequestMapping("/activemq")
public class ActivemqController {
	
	@Resource
	QueueSender queueSender;
	
	@Resource 
	TopicSender topicSender;
	
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message")String message){
		String opt = "";
		try{			
			this.queueSender.send("test.queue", message);
			opt = "suc";
		}catch(Exception e){
			opt = e.getClass().toString();
		}
		return opt;
	}
	
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message")String message){
		String opt = "";
		try{			
			this.topicSender.send("test.topic", message);
			opt = "suc";
		}catch(Exception e){
			opt = e.getClass().toString();
		}
		return opt;
	}
}
