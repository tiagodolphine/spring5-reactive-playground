package com.dolphine.spring.playground.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 21/04/17.
 */
public class SlowClient {

    private static final Logger logger = LoggerFactory.getLogger(SlowClient.class);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        WebClient webClient = WebClient.create();
        Mono<String>
                promise =
                webClient.get()
                        .uri("http://localhost:7337/slow")
                        .exchange()
                        .flatMap(response -> response.bodyToMono(String.class));

        logger.info("MONO returned at {}", LocalDateTime.now());

        promise.subscribe(r -> {
            logger.info("RESPONSE returned at {}", LocalDateTime.now());
            countDownLatch.countDown();
        });

        countDownLatch.await();

    }
}
