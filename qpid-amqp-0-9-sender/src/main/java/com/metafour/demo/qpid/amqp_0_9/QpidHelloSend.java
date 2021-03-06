package com.metafour.demo.qpid.amqp_0_9;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import java.util.Properties;

public class QpidHelloSend {
  
  private static final Logger logger = Logger.getLogger(QpidHelloSend.class);
  
  public QpidHelloSend() {}

  public static void main(String[] args) {
    QpidHelloSend producer = new QpidHelloSend();
    producer.runTest();
  }

  private void runTest() {
    try {
      Properties properties = new Properties();
      properties.load(this.getClass().getResourceAsStream("/hello.properties"));
      Context context = new InitialContext(properties);

      ConnectionFactory connectionFactory =
          (ConnectionFactory) context.lookup("qpidConnectionfactory");
      Connection connection = connectionFactory.createConnection();
      connection.start();

      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      Destination destination = (Destination) context.lookup("topicExchange");

      MessageProducer messageProducer = session.createProducer(destination);

      TextMessage message = session.createTextMessage("Hello AMQP 0.9 world!");
      messageProducer.send(message);
      logger.info("Message sent!");

      connection.close();
      context.close();
    } catch (Exception e) {
      logger.fatal(e.getMessage(), e);
    }
  }
}
