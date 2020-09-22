import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "invalid video duration contract"
    description """
    video list contract
 
    ```
    given:
        video list duration is the even multiple of 25
    when:
        we get the video list with this duration
    then:
        we will get an error
    ```
 """

    request {
        method 'GET'
        urlPath('/videos') {
            queryParameters {
                parameter("duration", 50)
            }
            headers {
                contentType(applicationJson())
            }
        }
    }

    response {
        status(412)
        body('''{
"message": "Invalid duration {{request.query.duration}}"
}''')
        headers {
            contentType(applicationJson())
        }

    }
}