package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Value;

import com.playtika.automation.school.test.framework.utils.Utils;

@Value
public class RegistrationRequest {

    public String email = Utils.emailGenerator();
    public String password = Utils.passwordGenerator();
}