package com.playtika.automation.school.test.framework;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.action.ServiceActions;
import com.playtika.automation.school.test.framework.configuration.AuthConfiguration;
import com.playtika.automation.school.test.framework.configuration.ServiceConfiguration;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;

@SpringBootTest(classes = {
        AuthConfiguration.class,
        ServiceConfiguration.class
} )
class ServiceTest {
    @Autowired
    private AuthActions authActions;

    @Autowired
    private ServiceActions serviceActions;

//Generate random email and password.
//Register user
//Authenticate and get token

    @Test
    void getAuthResponseTest() throws InterruptedException {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        RegistrationResponse registrationResponse = serviceActions.getRegistration(registrationRequest);


        AuthResponse authResponse = authActions.getAuthentication(); //new creds generated, fix

        System.out.println(authResponse.getAccessToken());
        System.out.println(registrationResponse.getUserId());
    }

}
