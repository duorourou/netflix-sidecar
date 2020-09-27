package com.github.duorourou.sidecar.sidecarapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
@EnableCircuitBreaker
public class SidecarAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SidecarAppApplication.class, args);
	}

}
