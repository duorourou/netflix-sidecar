package com.github.duorourou.sidecar.contentservice

import com.github.duorourou.sidecar.contentservice.video.Video
import org.springframework.http.MediaType

class VideoListContractSpec extends BaseVideoContractSpec {

    def "we will get the video lsit when the duration is the odd multiple of 25"() {
        expect:
        webTestClient.get()
                .uri("/videos?duration=25"
                )
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Video.class).hasSize(3)
    }

}
