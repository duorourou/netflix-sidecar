package com.github.duorourou.sidecar.videoservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static reactor.core.publisher.Flux.error;

@RestController
@RequestMapping(path = "/videos")
public class VideoEndPoint {
    private static final Logger logger = LoggerFactory.getLogger(VideoEndPoint.class);

    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    @GetMapping
    public Flux<VideoResponse> list() {
        logger.info("received a list request");
        final int duration = atomicInteger.incrementAndGet();
        if (duration / 25 % 2 == 0) {
            logger.info("sliding error window {}", duration);
            return error(new RuntimeException("Error Duration " + duration));
        }
        return Flux.fromArray(new VideoResponse[
                ]{
                new VideoResponse("梅西经典进球", "煤老板"),
                new VideoResponse("C罗十大远射破门", "可以吸的罗纳尔多"),
                new VideoResponse("李毅大帝经典护球", "直播8")
        }).delayElements(Duration.ofMillis(duration / 20));
    }
}
