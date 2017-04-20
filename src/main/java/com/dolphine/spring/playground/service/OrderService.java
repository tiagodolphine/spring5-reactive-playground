package com.dolphine.spring.playground.service;

import com.dolphine.spring.playground.client.CustomerClient;
import com.dolphine.spring.playground.model.Customer;
import com.dolphine.spring.playground.model.Order;
import com.dolphine.spring.playground.repository.OrderRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@Service
public class OrderService implements InitializingBean {

    private CustomerClient customerClient;

    private OrderRepository orderRepository;

    public OrderService(CustomerClient customerClient, OrderRepository orderRepository) {
        this.customerClient = customerClient;
        this.orderRepository = orderRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //Inserting test data !!!
        orderRepository.deleteAll()
                .then(save(Order.builder()
                        .id(UUID.randomUUID().toString())
                        .customer(Customer.builder().id(UUID.randomUUID().toString()).build())
                        .items(Arrays.asList("Hamburguer"))
                        .value(new BigDecimal(100))
                        .timestamp(new Date())
                        .build()))
                .subscribe();
    }

    public Flux<Order> list() {
        return orderRepository.findAll()
                .flatMap(order -> customerClient.get(order.getCustomer().getId())
                        .flatMap(customer -> {
                            order.setCustomer(customer);
                            return Mono.just(order);
                        })
                );
    }

    public Mono<Order> save(Order order) {
        return orderRepository.save(order).log();
    }
}
