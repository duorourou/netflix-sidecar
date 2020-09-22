import com.github.tomakehurst.wiremock.common.Json
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "video list contract"
    description """
    video list contract
 
    ```
    given:
        video list duration is the odd multiple of 25
    when:
        we get the video list with this duration
    then:
        we will get the video list
    ```
 """

    request {
        method 'GET'
        urlPath('/videos') {
            queryParameters {
                parameter("duration", 25)
            }
            headers {
                contentType(applicationJson())
            }
        }
    }

    response {
        status(200)
        body(Json.write([
                new VideoResponse("梅西经典进球", "煤老板"),
                new VideoResponse("C罗十大远射破门", "可以吸的罗纳尔多"),
                new VideoResponse("李毅大帝经典护球", "直播8")
        ]))
        headers {
            contentType(applicationJson())
        }

    }
}

class VideoResponse implements Serializable {
    def name
    def author

    VideoResponse(name, author) {
        this.name = name
        this.author = author
    }
}