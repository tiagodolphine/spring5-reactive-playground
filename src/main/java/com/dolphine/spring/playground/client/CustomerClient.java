package com.dolphine.spring.playground.client;

import com.dolphine.spring.playground.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@Component
public class CustomerClient {

    private WebClient webClient;

    public CustomerClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Customer> get(String id) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Mono.create(monoSink -> {
            monoSink.success(Customer.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Tiago Dolphine")
                    .age(Optional.of(33))
                    .phone(Optional.of("111111111111"))
                    .build());
        });

        //        return webClient.get()
        //                .uri("http://localhost:9000/customers/{id}", id)
        //                .accept(MediaType.APPLICATION_JSON)
        //                .exchange()
        //                .then(response -> response.bodyToMono(Customer.class))
        //                .log();
    }

}
