package com.github.duorourou.sidecar.contentservice


import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification


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
