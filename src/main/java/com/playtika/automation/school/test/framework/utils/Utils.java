package com.playtika.automation.school.test.framework.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.RandomStringUtils;

@UtilityClass
public class Utils {

    public String emailGenerator() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString + "@mail.com";
    }

    public String passwordGenerator() {
        return RandomStringUtils.randomAlphabetic(6);
    }
}