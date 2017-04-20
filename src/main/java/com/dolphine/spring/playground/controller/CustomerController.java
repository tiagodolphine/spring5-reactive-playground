package com.dolphine.spring.playground.controller;

import com.dolphine.spring.playground.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/04/17.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Customer> get(@PathVariable String id) {
        logger.info("Get Customer {}", id);
        //just a mock for testing
        return Mono.just(Customer.builder()
                .id(id)
                .name("Tiago Dolphine")
                .age(Optional.of(33))
                .phone(Optional.of("111111111111"))
                .build());
    }
}
