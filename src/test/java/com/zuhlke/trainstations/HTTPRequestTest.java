package com.zuhlke.trainstations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTTPRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void canAccessHomePage() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("_links");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("station");
    }
}