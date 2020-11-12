package com.playtika.automation.school.test.framework.pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("token_type")
    String tokenType;

    @JsonProperty("expires_in")
    String expiresIn;

    @JsonProperty("scope")
    String scope;

}

/*
{"access_token":"230527cd-0da3-48f1-a0c8-bb946c0a9af3",
        "token_type":"bearer",
        "expires_in":51765,
        "scope":"read write"}*/
