package com.playtika.automation.school.test.framework.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Note {

    Integer id;
    String content;
    String createdAt;
    String modifiedAt;
    Integer version;
}