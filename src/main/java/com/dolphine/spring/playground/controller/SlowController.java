package com.dolphine.spring.playground.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 21/04/17.
 */
@RestController
@RequestMapping("/slow")
public class SlowController {

    @GetMapping
    public Mono<String> get() {
        return Mono.just("I'm a slow response!").delaySubscription(Duration.ofSeconds(3));
    }
}
