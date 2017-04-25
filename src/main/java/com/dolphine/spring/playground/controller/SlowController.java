package com.dolphine.spring.playground.controller;

import com.dolphine.spring.playground.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 21/04/17.
 */
@RestController
@RequestMapping("/slow")
public class SlowController {

    @GetMapping("/{id}")
    public Mono<Customer> get(@PathVariable String id) {
        Customer customer = Customer.builder()
                        .id(id)
                        .name("Tiago Dolphine")
                        .age(Optional.of(33))
                        .phone(Optional.of("111111111111"))
                        .build();

        return Mono.just(customer).delaySubscription(Duration.ofSeconds(2));
    }
}
