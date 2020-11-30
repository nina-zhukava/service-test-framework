package com.playtika.automation.school.test.framework.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.playtika.automation.school.test.framework.pojo.Note;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;

@FeignClient(
        name = "service-feign-client",
        url = "${test.service.host}",
        path = "v1"
)
public interface ServiceFeignClient {

    @PostMapping(value = "accounts")
    void registerUser(@RequestBody RegistrationRequest request);

    @PostMapping(value = "notes")
    Note addNewNote(@RequestHeader("Authorization") String authorization,
                    @RequestBody CreateNoteRequest request);

    @GetMapping(value = "notes")
    List<Note> getUserNotes(@RequestHeader("Authorization") String authorization);

    @PutMapping(value = "notes/{noteId}")
    String updateNote(@RequestHeader("Authorization") String authorization,
                      @PathVariable("noteId") int noteId,
                      @RequestBody UpdateNoteRequest request);

    @GetMapping(value = "notes/{noteId}")
    Note getNoteById(@RequestHeader("Authorization") String authorization,
                     @PathVariable("noteId") int noteId);

    @DeleteMapping(value = "notes/{noteId}")
    void deleteNoteById(@RequestHeader("Authorization") String authorization,
                        @PathVariable("noteId") int noteId);
}