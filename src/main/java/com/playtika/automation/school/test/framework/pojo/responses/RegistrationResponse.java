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
public class RegistrationResponse {
    /*    {
            "id": 853,
                "email": "test@email",
                "registeredAt": "2020-10-29T08:52:29.705"
        }*/ //save id for using
//do I really need it? don't think so
    @JsonProperty("id")
    int userId;
}
