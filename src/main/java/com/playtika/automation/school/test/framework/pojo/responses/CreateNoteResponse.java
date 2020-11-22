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
public class CreateNoteResponse {

    @JsonProperty("id")
    int noteId;

    String content;
    String createdAt;
    String modifiedAt;
    int version;

}