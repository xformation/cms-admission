package com.synectiks.admission.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


//  @MessageMapping("/hello")
//  @SendTo("/topic/greetings")
//  public Greeting greeting(HelloMessage message) throws Exception {
//    Thread.sleep(500); // simulated delay
//    return new Greeting("Hello, I am from Admission service ::::::: ");
//  }

  private final SimpMessagingTemplate template;
  
  @Autowired
  GreetingController(SimpMessagingTemplate template){
	  this.template = template;
  }
  
  @MessageMapping("/send/greeting")
  public void onReceiveMessage(String message) {
	  this.template.convertAndSend("/topic", "Hello, I am from Admission service ::::::: ");
  }
}
