package com.dolphine.spring.playground.controller;

import com.dolphine.spring.playground.model.Order;
import com.dolphine.spring.playground.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Order> list() {
        logger.info("List");
        Flux<Order> response = orderService.list();
        return response;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> post(Mono<Order> orderMono) {
        logger.info("Post");
        Mono<Void> response = orderService.save(orderMono);
        return response;
    }

    @GetMapping(path = "/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Order> stream() {
        Flux<Order>
                orderFlux =
                Flux.fromStream(Stream.generate(() -> Order.builder()
                        .id(UUID.randomUUID().toString())
                        .timestamp(new Date())
                        .build()));

       return Flux.zip(orderFlux, Flux.interval(Duration.ofSeconds(1))).map(Tuple2::getT1);
    }

}
