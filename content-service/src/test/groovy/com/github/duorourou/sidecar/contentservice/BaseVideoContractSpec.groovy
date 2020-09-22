package com.github.duorourou.sidecar.contentservice

import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification


@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = ["com.github.duorourou.sidecar:video-service:+:stubs:8090"])
class BaseVideoContractSpec extends Specification{

    @StubRunnerPort("video-service")
    private int port

    WebTestClient webTestClient

    def setup() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + this.port).build()
    }

}
