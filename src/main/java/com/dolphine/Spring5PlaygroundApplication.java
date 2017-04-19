package com.dolphine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@ComponentScan("com.dolphine.spring.playground")
public class Spring5PlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring5PlaygroundApplication.class, args);
    }
}
