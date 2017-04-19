package com.dolphine.spring.playground.service;

import com.dolphine.spring.playground.client.CustomerClient;
import com.dolphine.spring.playground.model.Customer;
import com.dolphine.spring.playground.model.Order;
import com.dolphine.spring.playground.repository.OrderRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
                .then(save(Mono.just(Order.builder()
                        .id(UUID.randomUUID().toString())
                        .customer(Customer.builder().id(UUID.randomUUID().toString()).build())
                        .items(Arrays.asList("Hamburguer"))
                        .value(new BigDecimal(100))
                        .timestamp(new Date())
                        .build())))
                .subscribe();
    }

    public Flux<Order> list() {
        return orderRepository.findAll()
                .doOnNext(order -> customerClient.get(order.getCustomer().getId())
                        .subscribe(customer -> order.setCustomer(customer)));
    }

    @Transactional
    public Mono<Order> save(Mono<Order> orderMono) {
        return orderMono.flatMap(order -> orderRepository.save(order));
    }
}
