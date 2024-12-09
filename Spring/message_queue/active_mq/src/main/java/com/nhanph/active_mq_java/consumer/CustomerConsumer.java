package com.nhanph.active_mq_java.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerConsumer {

//    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @JmsListener(destination = "nhan-queue")
    public void consumeMessage(String message) {
        System.out.println("Message received from activemq queue---"+message);
//        logger.info("Message received from activemq queue---"+message);
    }
}