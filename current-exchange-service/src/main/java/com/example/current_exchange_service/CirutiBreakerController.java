package com.example.current_exchange_service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CirutiBreakerController {

    private Logger logger = LoggerFactory.getLogger(CirutiBreakerController.class);

    @GetMapping("sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodeResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodeResponse")
//    @RateLimiter(name = "default")
    // 10s cho phép 10.000 lệnh gọi
    @Bulkhead(name = "default")
    public String sampleApi(){
        logger.info("Sample API call receired");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",String.class);
//        return forEntity.getBody();
        return "sample";
    }

    // nếu xử lý quay lại vài lần mà vẫn bị lỗi thì sẽ gọi method dự phòng này trả về
    public String hardcodeResponse(Exception e){
        return "fallback";
    }
}
