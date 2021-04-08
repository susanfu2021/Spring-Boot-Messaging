package jee3060.biway.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jee3060.biway.producer.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookProducer {
    @Value("${rabbit.book.queue}")
    private String queuename;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send (Book book) {
        try {
            log.info (" Sending a book message .....");
            log.info (book.getTitle());
            log.info (book.getAuthor());
            log.info (book.getCategory());
            log.info(book.getPublished());
            rabbitTemplate.convertAndSend(queuename, objectMapper.writeValueAsString(book));
        } catch (JsonProcessingException e) {
            log.error (e.getMessage());
        }
    }
}
