package com.github.duorourou.sidecar.favoriteservice.video.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class VideoCircuitBreakerConfig {

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindow(3, 5, COUNT_BASED)
                        .failureRateThreshold(30)
                        .waitIntervalFunctionInOpenState(IntervalFunction.of(Duration.ofSeconds(10)))
                        .recordExceptions(Exception.class)
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofMillis(6000L))
                        .build())
                .build());
    }

    @Bean
    public TimeLimiterConfig timeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(1000L))
                .build();
    }

    @Bean
    public TimeLimiter favoriteVideosTimeLimiter(TimeLimiterConfig timeLimiterConfig) {
        return TimeLimiter.of(timeLimiterConfig);
    }

    @Bean
    @DependsOn("defaultCustomizer")
    public ReactiveCircuitBreaker videoCircuitBreaker(ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory) {
        return reactiveCircuitBreakerFactory.create("videoCircuitBreaker");
    }
}
