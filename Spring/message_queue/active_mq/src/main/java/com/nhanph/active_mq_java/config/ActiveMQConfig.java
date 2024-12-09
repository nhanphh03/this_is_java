package com.nhanph.active_mq_java.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.JmsPoolConnectionFactoryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import java.util.List;

@EnableJms
@Configuration
public class ActiveMQConfig {

    @Value(value = "${spring.activemq.brokerUrl}")
    private String brokerUrl;

    @Value(value = "${spring.activemq.user}")
    private String brokerUsername;

    @Value(value = "${spring.activemq.password}")
    private String brokerPassword;

    @Value(value = "${spring.activemq.pool.max-connections}")
    private int brokerMaxConnections;

    @Bean(name = "demoConnectionPoolActiveMQ")
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        var activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setUserName(brokerUsername);
        activeMQConnectionFactory.setPassword(brokerPassword);
        activeMQConnectionFactory.setTrustAllPackages(false);
        activeMQConnectionFactory.setTrustedPackages(List.of(
                "com.nhanph.active_mq_java",
                "java.util",
                "java.lang"
        ));

        activeMQConnectionFactory.setOptimizeAcknowledge(true);
        activeMQConnectionFactory.setAlwaysSessionAsync(false);
        activeMQConnectionFactory.setAlwaysSyncSend(true);
        activeMQConnectionFactory.setSendAcksAsync(true);

        var activeMQPrefetchPolicy = new ActiveMQPrefetchPolicy();
        activeMQPrefetchPolicy.setQueuePrefetch(1000);

        var jms = new JmsPoolConnectionFactoryProperties();
        jms.setMaxConnections(brokerMaxConnections);
        jms.setEnabled(true);
        return activeMQConnectionFactory;
    }

    @Bean(name = "demoJmsTemplate")
    public JmsTemplate jmsTemplate(@Qualifier("demoConnectionPoolActiveMQ") ConnectionFactory connectionFactory) {
        var jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setSessionTransacted(true);
        jmsTemplate.setDeliveryPersistent(false);
        return jmsTemplate;
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("nhan  -queue");
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactory(
            @Qualifier("demoConnectionPoolActiveMQ") ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(new CachingConnectionFactory(connectionFactory));
        factory.setConcurrency("1-1"); // Adjust concurrency as needed
        return factory;
    }
}


//
//Explanation:
//JmsListenerContainerFactory Bean: This bean is necessary for setting up JMS listeners. It configures the listener container that will handle incoming messages.
//CachingConnectionFactory: This is used to wrap the ConnectionFactory to improve performance by caching connections.
//        Concurrency: You can adjust the concurrency settings based on your application's needs. The example uses a simple "1-1" setting, which means a single consumer thread.
//Make sure to import the necessary classes for DefaultJmsListenerContainerFactory, JmsListenerContainerFactory, and CachingConnectionFactory. This setup should resolve the error and allow your application to start successfully.