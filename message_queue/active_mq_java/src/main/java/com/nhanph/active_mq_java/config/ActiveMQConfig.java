package com.nhanph.active_mq_java.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.JmsPoolConnectionFactoryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
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
        activeMQConnectionFactory.setBrokerURL(brokerUrl); //Định nghĩa URL của máy chủ ActiveMQ để kết nối.
        activeMQConnectionFactory.setUserName(brokerUsername); //Định nghĩa tên đăng nhập để kết nối đến máy chủ ActiveMQ.
        activeMQConnectionFactory.setPassword(brokerPassword); //Định nghĩa mật khẩu để kết nối đến máy chủ ActiveMQ.
        activeMQConnectionFactory.setTrustAllPackages(false); // Tắt cho phép mọi loại đối tượng
        activeMQConnectionFactory.setTrustedPackages(List.of( //Tắt tính năng cho phép tất cả các package được deserialize. Giá trị mặc định là false
                "com.nhanph.active_mq_java", //Định nghĩa danh sách các package được phép deserialize.
                "java.util",// Trong ví dụ này chỉ cho phép các class trong gói "com.example.myapp.dto", "java.util" và "java.lang".
                "java.lang"
        ));

        activeMQConnectionFactory.setOptimizeAcknowledge(true); //Bật tính năng tối ưu hóa xác nhận (acknowledgment) cho các kết nối JMS.
        activeMQConnectionFactory.setAlwaysSessionAsync(false); //Định nghĩa xem các phiên làm việc JMS luôn hoạt động
        // theo cách Asynchronous hay không.
        activeMQConnectionFactory.setAlwaysSyncSend(true); //Bật tính năng gửi tin nhắn luôn đồng bộ.
        activeMQConnectionFactory.setSendAcksAsync(true); //Bật tính năng xác nhận gửi tin nhắn Asynchronous.

        var activeMQPrefetchPolicy = new ActiveMQPrefetchPolicy();
        activeMQPrefetchPolicy.setQueuePrefetch(1000); //Định nghĩa số lượng tin nhắn được prefetch cho các kết nối queue.

        var jms = new JmsPoolConnectionFactoryProperties();
        jms.setMaxConnections(brokerMaxConnections);
        jms.setEnabled(true);
        return activeMQConnectionFactory;
    }


    @Bean(name = "demoJmsTemplate")
    public JmsTemplate jmsTemplate(@Qualifier("demoConnectionPoolActiveMQ") ConnectionFactory connectionFactory) {
        var jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setSessionTransacted(true);//Bật tính năng giao dịch tự động (transactional) cho phiên làm việc JMS
        // . Điều này đảm bảo tính nhất quán của giao dịch khi gửi và nhận tin nhắn.
        jmsTemplate.setDeliveryPersistent(false);//Định nghĩa cách thức giao tiếp tin nhắn:
            //True: Tin nhắn sẽ được lưu trữ vĩnh viễn trên máy chủ ActiveMQ.
            //False: Tin nhắn sẽ được xóa sau khi được đọc một lần.
        return jmsTemplate;
    }





}
