package com.example.catalogservice.messagequeue;

import com.example.catalogservice.entity.BookCatalogEntity;
import com.example.catalogservice.repository.BookCatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final BookCatalogRepository bookCatalogRepository;

    //topics: 지정한 topic으로 데이터가 들어올 경우 리스너를 통해 해당 메세지가 전달된다.
    //groupId: Kafka에서 토픽에 쌓여있는 메세지를 가져가는 컨슈머를 그룹핑(여러 컨슈머가 있을 경우 그룹되어진 컨슈머를 지정해서 가져옴)
    @KafkaListener(topics = "${spring.kafka.topics.order-to-catalog}", groupId = "${spring.kafka.consumer.group-ids.order-catalog}")
    public void updateQuantity(String kafkaMessage) {
        log.info("Kafka Message: -> " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            map = objectMapper.readValue(kafkaMessage, new TypeReference<>() {
            });
        } catch (JsonProcessingException jpe) {
            log.error(jpe.getOriginalMessage());
        }

        BookCatalogEntity bookCatalogEntity = bookCatalogRepository.findByBookId((String) map.get("bookId"));
        if (bookCatalogEntity != null) {
            bookCatalogEntity.minusStock((Integer) map.get("qty"));
            bookCatalogRepository.save(bookCatalogEntity);
        }
    }

}
