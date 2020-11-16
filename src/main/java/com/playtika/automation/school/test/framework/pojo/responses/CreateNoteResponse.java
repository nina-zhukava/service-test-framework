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
//{"id":1143,"content":"string","createdAt":"2020-11-15T16:06:40.088","modifiedAt":"2020-11-15T16:06:40.088","version":0}