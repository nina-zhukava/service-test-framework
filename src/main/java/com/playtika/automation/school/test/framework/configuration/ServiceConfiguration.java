package com.playtika.automation.school.test.framework.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.playtika.automation.school.test.framework.action.ServiceActions;
import com.playtika.automation.school.test.framework.client.ServiceFeignClient;

@EnableAutoConfiguration
@Configuration
@EnableFeignClients(clients = {
        ServiceFeignClient.class
})
public class ServiceConfiguration {

    @Bean
    public ServiceActions serviceActions(ServiceFeignClient serviceFeignClient){
        return new ServiceActions(serviceFeignClient);
    }

}
