package com.playtika.automation.school.test.framework.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;

@FeignClient(
        name = "auth-feign-client",
        url = "${test.service.host}"
)
public interface AuthFeignClient {

    @PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded")
    AuthResponse authentication(@RequestHeader("Authorization") String authorization,
                                @RequestParam("grant_type") String grantType,
                                @RequestParam("scope") String scope,
                                @RequestParam("username") String userName,
                                @RequestParam("password") String password);
}