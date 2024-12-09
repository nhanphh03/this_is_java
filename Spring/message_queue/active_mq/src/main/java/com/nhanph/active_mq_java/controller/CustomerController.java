package com.nhanph.active_mq_java.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhanph.active_mq_java.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping("/produce")
public class CustomerController {

    private final JmsTemplate jmsTemplate;

    private final Queue queue;

    public CustomerController(JmsTemplate jmsTemplate, Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    @PostMapping("/message")
    public Customer sendMessage() {
        Customer student = new Customer();

        try {
            ObjectMapper mapper = new ObjectMapper();
            String studentAsJson = mapper.writeValueAsString(student);

            jmsTemplate.convertAndSend(queue, studentAsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
}