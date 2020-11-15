package com.playtika.automation.school.test.framework.action;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.AuthFeignClient;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;

@AllArgsConstructor
public class AuthActions {

    private final AuthFeignClient authFeignClient;
    private final String authorization111;
    private final String grantType;
    private final String scope;

    @Step("Authenticate")
    public AuthResponse getAuthentication(String username, String password) {
        return authFeignClient.authentication(authorization111, grantType, scope, username, password);
    }
}
