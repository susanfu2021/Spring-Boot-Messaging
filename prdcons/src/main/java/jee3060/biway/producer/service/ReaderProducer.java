package jee3060.biway.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jee3060.biway.producer.model.Reader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ReaderProducer {
    @Value("${rabbit.reader.queue}")
    private String queuename;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send (Reader reader) {
        try {
            log.info (" ==========>  Sending a reader message =====>>");
            log.info (reader.getFirstname());
            log.info (reader.getLastname());
            rabbitTemplate.convertAndSend(queuename, objectMapper.writeValueAsString(reader));
        } catch (JsonProcessingException e) {
            log.error (e.getMessage());
        }
    }
}
