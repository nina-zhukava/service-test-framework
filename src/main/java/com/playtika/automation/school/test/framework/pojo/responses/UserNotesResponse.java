package com.playtika.automation.school.test.framework.pojo.responses;

import java.util.List;

import lombok.Value;

import com.playtika.automation.school.test.framework.pojo.Note;

@Value
public class UserNotesResponse {
    List<Note> notes;
}
