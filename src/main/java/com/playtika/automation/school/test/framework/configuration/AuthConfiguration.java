package com.playtika.automation.school.test.framework.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.client.AuthFeignClient;

@EnableAutoConfiguration
@Configuration
@EnableFeignClients(clients = AuthFeignClient.class)
public class AuthConfiguration {

/*    @Value("${auth.token}")
    String authorization;*/

    String authorization = "Basic dGVzdDpzZWNyZXQ=";

    @Bean
    public AuthActions authActions(AuthFeignClient authFeignClient){
        return new AuthActions(authFeignClient, authorization, "password", "read write",
                               "test@email", "password");
    }




}
