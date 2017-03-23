package com.dolphine.spring.playground.repository;

import com.dolphine.spring.playground.model.Order;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@Repository
public class OrderRepository {

    private RedisConnectionFactory redisConnectionFactory;
    private static final String PREFIX = Order.class.getSimpleName();
    private static final String KEY_PATTERN = PREFIX + "*";
    private RedisSerializer<String> serializer = new StringRedisSerializer();
    private RedisSerializer<Order> valueSerializer = new Jackson2JsonRedisSerializer(Order.class);

    public OrderRepository(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    public Flux<Order> findAll() {
        ReactiveRedisConnection connection = redisConnectionFactory.getReactiveConnection();
        //getting all keys
        Mono<List<ByteBuffer>> keys = connection.keyCommands() //
                .keys(ByteBuffer.wrap(serializer.serialize(KEY_PATTERN)));

        //getting all values
        return Flux.create(fluxSink -> {
            keys.subscribe(keyList -> {
                connection.stringCommands().mGet(keyList).subscribe(list -> {
                    Flux.fromIterable(list)
                            .doOnComplete(() -> fluxSink.complete())
                            .subscribe(value -> fluxSink.next(valueSerializer.deserialize(value.array())));
                });
            });

        });
    }

    public Mono<Boolean> save(Mono<Order> orderMono) {
        ReactiveRedisConnection connection = redisConnectionFactory.getReactiveConnection();
        return orderMono.then(order -> connection.stringCommands()
                .set(ByteBuffer.wrap(serializer.serialize(PREFIX + ":" + order.getId())),
                        ByteBuffer.wrap(valueSerializer.serialize(order))));
    }
}
