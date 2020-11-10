package com.playtika.automation.school.test.framework.pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class AuthResponse {
    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("token_type")
    String tokenType;

}

/*
{"access_token":"230527cd-0da3-48f1-a0c8-bb946c0a9af3",
        "token_type":"bearer",
        "expires_in":51765,
        "scope":"read write"}*/
