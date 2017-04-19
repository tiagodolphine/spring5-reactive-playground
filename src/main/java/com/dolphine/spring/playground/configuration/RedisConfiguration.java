//package com.dolphine.spring.playground.configuration;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
//import javax.annotation.PreDestroy;
//
///**
// * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
// */
//@Configuration
//public class RedisConfiguration implements InitializingBean {
//
//
////    @Lazy
////    @Autowired
////    private OrderService orderService;
//
//    @Bean
//    public ReactiveRedisConnectionFactory redisConnectionFactory2(@Value("${redis.host}") String host,
//                                                                  @Value("${redis.port}") int port) {
//        return new LettuceConnectionFactory(host, port);
//    }
//
//    /**
//     * Clear database before shut down.
//     */
//    @PreDestroy
//    public void flushTestDb() {
//        //factory.getReactiveConnection()..flushDb();
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
////        orderService.save(Mono.just(Order.builder()
////                .id(UUID.randomUUID().toString())
////                .customer(Customer.builder().id(UUID.randomUUID().toString()).build())
////                .items(Arrays.asList("Hamburguer"))
////                .value(new BigDecimal(100))
////                .timestamp(new Date())
////                .build())).block();
//    }
//}
