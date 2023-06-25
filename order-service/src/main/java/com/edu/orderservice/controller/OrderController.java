package com.edu.orderservice.controller;

import com.edu.orderservice.dto.Order;
import com.edu.orderservice.dto.OrderEvent;
import com.edu.orderservice.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        orderProducer.sendMessage(OrderEvent
                .builder()
                .message("ORDER status is inPending")
                .status("PENDING")
                .order(order)
                .build());

        return "ORDER PLCAED";
    }
}
