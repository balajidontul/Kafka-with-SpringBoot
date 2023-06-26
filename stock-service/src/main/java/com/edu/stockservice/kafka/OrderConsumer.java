package com.edu.stockservice.kafka;

import com.edu.basedomains.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {

    @Autowired
    private KafkaProperties kafkaProperties;

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){

        log.info("Order event received in Stock service:");
    }


}
