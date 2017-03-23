package com.dolphine.spring.playground.service;

import com.dolphine.spring.playground.client.CustomerClient;
import com.dolphine.spring.playground.model.Customer;
import com.dolphine.spring.playground.model.Order;
import com.dolphine.spring.playground.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 19/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private CustomerClient customerClientMock;

    @Mock
    private OrderRepository orderRepositoryMock;

    @Test
    public void list() throws Exception {

        Customer
                customerMock =
                Customer.builder().age(Optional.of(33)).name("Tiago").id(UUID.randomUUID().toString()).build();

        Order
                orderMock =
                Order.builder()
                        .id(UUID.randomUUID().toString())
                        .customer(Customer.builder().id(customerMock.getId()).build())
                        .build();

        Mockito.when(customerClientMock.get(Mockito.anyString())).thenReturn(Mono.just(customerMock));
        Mockito.when(orderRepositoryMock.findAll()).thenReturn(Flux.fromStream(Stream.of(orderMock)));

        Flux<Order> orderFlux = orderService.list();
        Order order = orderFlux.log().next().block();
        Assert.assertEquals(order, orderMock);
        Assert.assertEquals(order.getCustomer(), customerMock);
    }
}
