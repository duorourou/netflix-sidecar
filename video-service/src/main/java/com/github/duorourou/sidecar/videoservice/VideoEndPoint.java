package com.github.duorourou.sidecar.videoservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static reactor.core.publisher.Flux.error;

@RestController
@RequestMapping(path = "/videos")
public class VideoEndPoint {
    private static final Logger logger = LoggerFactory.getLogger(VideoEndPoint.class);

    @GetMapping
    public Flux<VideoResponse> list(@RequestParam("duration") Long duration) {
        logger.info("received a list request");
        if (duration / 25 % 2 == 0) {
            logger.info("sliding error window {}", duration);
            return error(InvalidDurationException.raise(duration));
        }
        return Flux.fromArray(new VideoResponse[
                ]{
                new VideoResponse("梅西经典进球", "煤老板"),
                new VideoResponse("C罗十大远射破门", "可以吸的罗纳尔多"),
                new VideoResponse("李毅大帝经典护球", "直播8")
        }).delayElements(Duration.ofMillis(duration / 20));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public Mono<ResponseEntity<String>> handler(InvalidDurationException durationException) {
        logger.error(durationException.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(durationException.getMessage()));
    }
}
