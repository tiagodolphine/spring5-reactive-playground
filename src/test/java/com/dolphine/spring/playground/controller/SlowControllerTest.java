package com.dolphine.spring.playground.controller;

import com.dolphine.spring.playground.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 19/04/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class SlowControllerTest {

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:7337").build();
    }

    @Test
    public void get() throws Exception {
        webTestClient.get().uri("/slow/123").exchange().expectStatus().isOk().expectBody(Customer.class);
    }
}
