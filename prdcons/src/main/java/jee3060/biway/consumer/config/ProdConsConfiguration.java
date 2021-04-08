package jee3060.biway.consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jee3060.biway.consumer.service.BookConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdConsConfiguration {
    @Value("${rabbit.book.queue}")
    private String bookqueue;
    @Value("${rabbit.book.exchange}")
    private String bookexchange;

    @Value("${rabbit.reader.queue}")
    private String readerqueue;
    @Value("${rabbit.reader.exchange}")
    private String readerexchange;

    @Bean
    public ObjectMapper objectMapper () {
        return new ObjectMapper();
    }

    @Bean
    public Queue bookqueue () {
        return new Queue (bookqueue, false);
    }

    @Bean
    public TopicExchange booktopicExchange () {
        return new TopicExchange (bookexchange);
    }

    @Bean
    public Binding bookbinding () {
        return BindingBuilder.bind(bookqueue()).to(booktopicExchange()).with(bookqueue);
    }

    @Bean
    public Queue readerqueue () {
        return new Queue (readerqueue, false);
    }

    @Bean
    public TopicExchange readertopicExchange () {
        return new TopicExchange (readerexchange);
    }

    @Bean
    public Binding readerbinding () {
        return BindingBuilder.bind(readerqueue()).to(readertopicExchange()).with(readerqueue);
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter (BookConsumer bookConsumer) {
        return new MessageListenerAdapter(bookConsumer, "receive");
    }

    @Bean
    public SimpleMessageListenerContainer container (ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(bookqueue);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }
}
