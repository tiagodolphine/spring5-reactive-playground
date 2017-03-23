package com.dolphine.spring.playground.configuration;

import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@EnableWebFlux
public class WebConfiguration extends WebFluxConfigurationSupport{
//    @Bean
//    MappingJackson2HttpMessageConverter messageConverter(){
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
//                .indentOutput(true)
//                .modulesToInstall(new Jdk8Module())
//                .modulesToInstall(new JSR310Module());
//        return new MappingJackson2HttpMessageConverter(builder.build());
//    }
}
