package com.github.duorourou.sidecar.videoservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static reactor.core.publisher.Flux.error;

@RestController
@RequestMapping(path = "/app")
public class HealthEndPoint {
    private static final Logger logger = LoggerFactory.getLogger(HealthEndPoint.class);

    @GetMapping(path = "/health")
    public ResponseEntity<Health> health() {
        return ResponseEntity.ok(Health.up().build());
    }
}
