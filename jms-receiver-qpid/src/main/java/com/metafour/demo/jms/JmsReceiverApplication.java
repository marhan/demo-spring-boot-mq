package com.metafour.demo.jms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class JmsReceiverApplication implements CommandLineRunner {

  private static final Logger logger = Logger.getLogger(JmsReceiverApplication.class);

  @Autowired
  AnnotationConfigApplicationContext context;
  
  @Autowired
  private JmsTemplate jmsTemplate;
  
  public static void main(String[] args) {
    SpringApplication.run(JmsReceiverApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("Waiting a few seconds...");
    Thread.sleep(10000);
    context.close();    
  }
  
}
