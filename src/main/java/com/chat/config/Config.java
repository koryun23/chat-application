package com.chat.config;

import com.chat.repository.ChatMessageRepository;
import com.chat.repository.MessageRepository;
import com.chat.repository.UserMessageRepository;
import com.chat.service.core.message.receiver.MessageReceiverService;
import com.chat.service.core.message.sender.MessageSenderService;
import com.chat.service.impl.message.receiver.MessageReceiverServiceWebSocketImpl;
import com.chat.service.impl.message.sender.MessageSenderServiceWebSocketImpl;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@Configuration
public class Config {

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    // the bean defined below will be used in sending a message to a single client (e.g. in personal chats)
    @Bean
    public Destination messageQueue() {
        return new ActiveMQQueue("message.queue");
    }

    // the bean defined below will be used in sending a message to several clients (e.g. in group chats)
    @Bean
    public Destination messageTopic() {
        return new ActiveMQTopic("message.topic");
    }

    @Bean
    public MessageSenderService messageSenderService(SimpMessagingTemplate simpMessagingTemplate) {
        return new MessageSenderServiceWebSocketImpl(simpMessagingTemplate);
    }

    @Bean
    public MessageReceiverService messageReceiverService() {
        return new MessageReceiverServiceWebSocketImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
