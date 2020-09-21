package com.github.duorourou.sidecar.contentservice.video;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Component
public class VideoEndPoint {
    Logger logger = LoggerFactory.getLogger(VideoEndPoint.class);

    private final ReactiveCircuitBreaker videoCircuitBreaker;
    private final WebClient.Builder webClientBuilder;

    public VideoEndPoint(ReactiveCircuitBreaker videoCircuitBreaker, WebClient.Builder webClientBuilder) {
        this.videoCircuitBreaker = videoCircuitBreaker;
        this.webClientBuilder = webClientBuilder;
    }

    public Flux<Video> list() {
        return videoCircuitBreaker.run(
                webClientBuilder
                        .build()
                        .get()
                        .uri("/video-service-sidecar/videos")
                        .retrieve().bodyToFlux(Video.class),
                (error) -> {
                    logger.info(error.getMessage());
                    return regularList();
                })
                .log();
    }

    private Flux<Video> regularList() {
        return Flux.fromArray(new Video[
                ]{
                new Video("一文看懂苹果最新发布会", "易新闻"),
                new Video("鸿蒙系统发布，华为或成最终赢家", "秋菊"),
                new Video("疫情之下中美关系走向", "CTV"),
                new Video("科技前沿:脑芯片植入才是人工智能最终解决方案？", "娱乐八卦")
        });
    }
}
