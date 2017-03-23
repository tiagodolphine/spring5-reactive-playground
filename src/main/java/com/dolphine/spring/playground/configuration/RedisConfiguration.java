package com.dolphine.spring.playground.configuration;

import com.dolphine.spring.playground.model.Customer;
import com.dolphine.spring.playground.model.Order;
import com.dolphine.spring.playground.service.OrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import reactor.core.publisher.Mono;

import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@Configuration
public class RedisConfiguration implements InitializingBean {

    @Autowired
    private RedisConnectionFactory factory;

    @Lazy
    @Autowired
    private OrderService orderService;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(@Value("${redis.host}") String host,
                                                         @Value("${redis.port}") int port) {
        return new LettuceConnectionFactory(host, port);
    }

    /**
     * Clear database before shut down.
     */
    @PreDestroy
    public void flushTestDb() {
        factory.getConnection().flushDb();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        orderService.save(Mono.just(Order.builder()
                .id(UUID.randomUUID().toString())
                .customer(Customer.builder().id(UUID.randomUUID().toString()).build())
                .items(Arrays.asList("Hamburguer"))
                .value(new BigDecimal(100))
                .timestamp(new Date())
                .build())).block();
    }
}
