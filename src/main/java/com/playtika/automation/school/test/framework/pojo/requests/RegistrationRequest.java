package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Value;
import org.apache.commons.lang.RandomStringUtils;

@Value
public class RegistrationRequest {

    private String emailGenerator() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString + "@mail.com";
    }

    private String passwordGenerator() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    public String email = emailGenerator();
    public String password = passwordGenerator();

}
