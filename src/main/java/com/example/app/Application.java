// demo-api: a small Spring Boot API. GET / returns service info;
// GET /actuator/health is the health probe. Listens on port 8080.
package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class Application {
    @GetMapping("/")
    public Map<String, Object> root() {
        return Map.of("service", "demo-api", "message", "Hello from Helio Pulse, delivered by a governed release", "runtime", "java/spring-boot", "ok", true);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
