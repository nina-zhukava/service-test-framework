package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNoteRequest {
    String content;
    public CreateNoteRequest(String content){
        this.content = content;
    }
}
