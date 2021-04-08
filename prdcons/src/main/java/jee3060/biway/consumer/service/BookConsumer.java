package jee3060.biway.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jee3060.biway.consumer.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class BookConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener
    public void receive(String jsonBook) {
        log.info ("<<<<==========Book Message Received <<==========" + jsonBook);
        try {
            Book book = objectMapper.readValue(jsonBook, Book.class);
            log.info (book.getTitle());
            log.info (book.getAuthor());
            log.info (book.getPublished());
            log.info (book.getCategory());
        } catch (IOException e) {
            log.error (e.getMessage());
        }
    }
}
