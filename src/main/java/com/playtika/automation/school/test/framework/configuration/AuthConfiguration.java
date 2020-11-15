package com.playtika.automation.school.test.framework.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.client.AuthFeignClient;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;

@EnableAutoConfiguration
@Configuration
@EnableFeignClients(clients = AuthFeignClient.class)
public class AuthConfiguration {

/*    @Value("${auth.token}")
    String authorization;*/

    String authorization = "Basic dGVzdDpzZWNyZXQ=";
    RegistrationRequest registrationRequest = new RegistrationRequest();

    @Bean
    public AuthActions authActions(AuthFeignClient authFeignClient){
/*        return new AuthActions(authFeignClient, authorization, "password", "read write",
                               "test@email", "password");*/
        return new AuthActions(authFeignClient, authorization, "password", "read write",
                               registrationRequest.getEmail(), registrationRequest.getPassword());
//        return new AuthActions(authFeignClient, authorization, "password", "read write", "string@123", "string");
    }




}
