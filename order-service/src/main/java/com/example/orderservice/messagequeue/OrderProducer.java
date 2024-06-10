package com.example.orderservice.messagequeue;

import static com.example.orderservice.messagequeue.KafkaTopics.ORDERS_BOOK;

import com.example.orderservice.dto.Field;
import com.example.orderservice.dto.KafkaOrderDto;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.Payload;
import com.example.orderservice.dto.Schema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private List<Field> fields = Arrays.asList(
            new Field("string",true,"order_id"),
            new Field("string",true,"user_id"),
            new Field("string",true,"book_id"),
            new Field("int32",true,"qty"),
            new Field("int32",true,"price"),
            new Field("int32",true,"total_price")
    );
    private Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name(ORDERS_BOOK)
            .build();

    public OrderDto sendOrder(String topic, OrderDto orderDto) {
        Payload payload = Payload.builder()
                .order_id(orderDto.getOrderId())
                .user_id(orderDto.getUserId())
                .book_id(orderDto.getBookId())
                .qty(orderDto.getQty())
                .price(orderDto.getPrice())
                .total_price(orderDto.getTotalPrice())
                .build();
        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(schema, payload);

        String orderData = "";
        try{
            orderData = objectMapper.writeValueAsString(kafkaOrderDto);
        }catch (JsonProcessingException jpe){
            log.error(jpe.getOriginalMessage());
        }
        kafkaTemplate.send(topic, orderData);
        log.info("Order Producer sent data :: "+kafkaOrderDto);
        return orderDto;
    }

}
