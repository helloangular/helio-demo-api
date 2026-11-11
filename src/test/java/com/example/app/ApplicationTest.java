package com.example.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class ApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate http;

    @Test
    void rootReturnsTheGoldenPathContract() {
        ResponseEntity<Map<String, Object>> response = http.exchange(
                "http://localhost:" + port + "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("service", "demo-api")
                .containsEntry("runtime", "java/spring-boot")
                .containsEntry("ok", true);
    }

    @Test
    void actuatorHealthIsAvailableForOrchestratorProbes() {
        ResponseEntity<Map<String, Object>> response = http.exchange(
                "http://localhost:" + port + "/actuator/health",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("status", "UP");
    }
}
