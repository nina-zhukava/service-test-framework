package com.playtika.automation.school.test.framework.action;

import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.AuthFeignClient;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;

@AllArgsConstructor
public class AuthActions {

    private final AuthFeignClient authFeignClient;
    private final String authorization;
    private final String grantType;
    private final String scope;

    public AuthResponse getAuthentication(String username, String password) {
        return authFeignClient.authentication(authorization, grantType, scope, username, password);
    }
}
