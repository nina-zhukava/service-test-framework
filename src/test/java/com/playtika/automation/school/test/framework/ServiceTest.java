package com.playtika.automation.school.test.framework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.configuration.AuthConfiguration;
import com.playtika.automation.school.test.framework.configuration.ServiceConfiguration;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;

@SpringBootTest(classes = {
        AuthConfiguration.class,
        ServiceConfiguration.class
} )
@ContextConfiguration(classes = {AuthConfiguration.class})
class ServiceTest {
    @Autowired
    private AuthActions authActions;

    @Test
    void getAuthResponseTest(){
        AuthResponse authResponse = authActions.getAuthToken();
        System.out.println(authResponse.getAccessToken());
    }

}
