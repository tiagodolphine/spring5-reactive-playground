package com.dolphine.spring.playground.service;

import com.dolphine.spring.playground.client.CustomerClient;
import com.dolphine.spring.playground.model.Order;
import com.dolphine.spring.playground.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@Service
public class OrderService {

    private CustomerClient customerClient;

    private OrderRepository orderRepository;

    public OrderService(CustomerClient customerClient, OrderRepository orderRepository) {
        this.customerClient = customerClient;
        this.orderRepository = orderRepository;
    }

    public Flux<Order> list() {
        Flux<Order> orderFlux = orderRepository.findAll();
        return orderFlux.doOnNext(order -> customerClient.get(order.getCustomer().getId())
                .subscribe(customer -> order.setCustomer(customer)));
    }

    public Mono<Void> save(Mono<Order> orderMono) {
        return Mono.create(sink -> orderRepository.save(orderMono).subscribe(ok -> {
            if (ok)
                sink.success();
            else
                sink.error(new RuntimeException("Error saving order"));
        }));
    }
}
