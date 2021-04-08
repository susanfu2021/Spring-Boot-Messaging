package jee3060.biway;

import jee3060.biway.producer.model.Reader;
import jee3060.biway.producer.service.ReaderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProdConsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdConsApplication.class, args);
    }

    @Autowired
    private ReaderProducer readerProducer;

    @Bean
    public void sendReader () {
        Reader reader = new Reader();

        reader.setFirstname("Mark");
        reader.setLastname("Smith");

        readerProducer.send(reader);
    }
}
