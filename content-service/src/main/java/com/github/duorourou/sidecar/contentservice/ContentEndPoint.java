package com.github.duorourou.sidecar.contentservice;

import com.github.duorourou.sidecar.contentservice.video.VideoEndPoint;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/contents")
public class ContentEndPoint {

    private final VideoEndPoint videoEndPoint;

    public ContentEndPoint(VideoEndPoint videoEndPoint) {
        this.videoEndPoint = videoEndPoint;
    }

    @GetMapping
    public Flux<ContentResponse> contents(ServerHttpResponse response) {
        response.getHeaders().add("api-version", "v1");

        return Flux.fromIterable(Arrays.asList(
                "Hello World",
                "Hola World",
                "Olá World",
                "Здравствуйте World",
                "안녕하십니까 World"
        )).zipWith(videoEndPoint.list(),
                (hello, video) -> {
                    return new ContentResponse(video.name(), video.author(), "VIDEO", hello);
                }
        );
    }

}
