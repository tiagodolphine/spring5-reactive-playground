package com.dolphine.spring.playground.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 19/03/17.
 */
@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
