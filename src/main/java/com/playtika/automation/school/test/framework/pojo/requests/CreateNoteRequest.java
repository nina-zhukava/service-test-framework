package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateNoteRequest {
    String content;
    public CreateNoteRequest(String text){
        content = text;
    }
}
