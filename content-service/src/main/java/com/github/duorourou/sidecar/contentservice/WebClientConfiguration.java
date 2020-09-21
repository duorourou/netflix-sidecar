package com.github.duorourou.sidecar.contentservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {


    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder(@Value("${sidecar.host:localhost}") String host,
                                                          @Value("${sidecar.port:5678}") String port) {
        return WebClient.builder().baseUrl(String.format("http://%s:%s", host, port));
    }
}
