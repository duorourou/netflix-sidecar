package com.github.duorourou.sidecar.favoriteservice;

import com.github.duorourou.sidecar.favoriteservice.video.VideoEndPoint;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/favorites")
public class FavoriteEndPoint {

	private final VideoEndPoint videoEndPoint;

	public FavoriteEndPoint(VideoEndPoint videoEndPoint) {
		this.videoEndPoint = videoEndPoint;
	}

	@GetMapping
	public Flux<FavoriteResponse> favorites(ServerHttpResponse response) {
		response.getHeaders().add("api-version", "v1");

		return Flux.fromIterable(Arrays.asList(
				"Hello World",
				"Hola World",
				"Olá World",
				"Здравствуйте World",
				"안녕하십니까 World"
		)).zipWith(videoEndPoint.list(),
				(hello, video) -> new FavoriteResponse(video.getName(), video.getAuthor(), "VIDEO", hello)
		);
	}

}
