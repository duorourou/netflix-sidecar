package com.github.duorourou.sidecar.favoriteservice


import org.springframework.http.MediaType

class InvalidVideoDurationContractSpec extends BaseVideoContractSpec {


    def "we will get an error when the duration is the even multiple of 25"() {
        expect:
        webTestClient.get()
                .uri("/videos?duration=50"
                )
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().is4xxClientError()
    }

}
