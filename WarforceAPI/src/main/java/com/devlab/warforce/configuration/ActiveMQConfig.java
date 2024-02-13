package com.devlab.warforce.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class ActiveMQConfig {
    public static final String BROKER_URL = "tcp://localhost:61616";

    public static final String BROKER_USERNAME = "admin";

    public static final String BROKER_PASSWORD = "admin";

    public static final String QUEUE_NAME = "warforce-queue";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUserName(BROKER_PASSWORD);

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();

        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(QUEUE_NAME);

        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");

        return factory;
    }
}
