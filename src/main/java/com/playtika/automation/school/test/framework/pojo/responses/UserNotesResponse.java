package com.playtika.automation.school.test.framework.pojo.responses;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.playtika.automation.school.test.framework.pojo.Note;

@Getter
@Setter
public class UserNotesResponse {

    List<Note> notes;
}