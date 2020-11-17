package com.playtika.automation.school.test.framework.pojo.responses;

import java.util.List;

import lombok.Value;

import com.playtika.automation.school.test.framework.pojo.Note;

@Value
public class GetUserNotesResponse {
    List<Note> notes;

}
/*
[{"id":1147,"content":"еуцгн","createdAt":"2020-11-16T08:20:55.497","modifiedAt":"2020-11-16T08:20:55.497","version":0},
        {"id":1143,"content":"string","createdAt":"2020-11-15T16:06:40.088","modifiedAt":"2020-11-15T16:06:40.088","version":0}]*/
