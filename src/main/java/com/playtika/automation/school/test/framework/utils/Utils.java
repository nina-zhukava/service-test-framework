package com.playtika.automation.school.test.framework.utils;

import org.apache.commons.lang.RandomStringUtils;

public class Utils {

    public static String emailGenerator() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString + "@mail.com";
    }

    public static String passwordGenerator() {
        return RandomStringUtils.randomAlphabetic(6);
    }

}
