package com.playtika.automation.school.test.framework.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Note {

/*    @JsonProperty("id")
    Integer noteId;*/

    Integer id;
    String content;
    String createdAt;
    String modifiedAt;
    Integer version;

}
//{"id":1147,"content":"еуцгн","createdAt":"2020-11-16T08:20:55.497","modifiedAt":"2020-11-16T08:20:55.497","version":0}