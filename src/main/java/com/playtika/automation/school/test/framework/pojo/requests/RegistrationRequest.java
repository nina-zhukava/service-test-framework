package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Getter;

import com.playtika.automation.school.test.framework.utils.Utils;

@Getter
public class RegistrationRequest {

    public String email = Utils.emailGenerator();
    public String password = Utils.passwordGenerator();
}