package jee3060.biway.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jee3060.biway.consumer.model.Reader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ReaderConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener
    public void receive(String jsonreader) {
        log.info ("<><> message received " + jsonreader);

        try {
            Reader reader = objectMapper.readValue(jsonreader, Reader.class);
            log.info (reader.getFirstname());
            log.info (reader.getLastname());
        } catch (IOException e) {
            log.error (e.getMessage());
        }
    }
}
