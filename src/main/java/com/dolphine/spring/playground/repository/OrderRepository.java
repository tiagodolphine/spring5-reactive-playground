package com.dolphine.spring.playground.repository;

import com.dolphine.spring.playground.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@Repository
public interface OrderRepository  extends ReactiveCrudRepository<Order, String> {

}
