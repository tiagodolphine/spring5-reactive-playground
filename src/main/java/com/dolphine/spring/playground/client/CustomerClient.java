package com.dolphine.spring.playground.client;

import com.dolphine.spring.playground.model.Customer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
        return webClient.get()
                .uri("http://localhost:7337/customers/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(Customer.class));
    }
}
