package jee3060.biway;

import jee3060.biway.producer.model.Book;
import jee3060.biway.producer.service.BookProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PubSubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubSubApplication.class, args);
    }

    @Autowired
    private BookProducer bookProducer;

    @Bean
    public void sendBook () {
        Book book = new Book();
        book.setTitle("A tale of two cities");
        book.setAuthor("Charles Dickens");
        book.setCategory("Classical");
        book.setPublished("1859");
        bookProducer.send (book);
    }
}
