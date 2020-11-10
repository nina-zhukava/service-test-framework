package com.playtika.automation.school.test.framework.action;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.AuthFeignClient;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;

@AllArgsConstructor
public class AuthActions {

    private final AuthFeignClient authFeignClient;
    private final String authorization;
//    private final String grantType;// = "password";
    private final String grant_type;// = "password";
    private final String scope;// = "read write";
    private final String username;// = "test@email";
    private final String password;//= "password";

    @Step("Authenticate")
    public AuthResponse getAuthToken() {
        return authFeignClient.authentication(authorization, grant_type, scope, username, password);
    }
}
