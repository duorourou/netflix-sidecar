package com.github.duorourou.sidecar.videoservice.contract

import com.github.duorourou.sidecar.videoservice.VideoEndPoint
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
class BaseContract extends Specification {

    @Autowired
    private VideoEndPoint videoEndPoint;

    @BeforeEach
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(videoEndPoint);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

}
