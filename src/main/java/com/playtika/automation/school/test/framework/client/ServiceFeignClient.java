package com.playtika.automation.school.test.framework.client;

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
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;

@FeignClient(
        name = "service-feign-client",
        url = "${test.service.host}",
        path = "v1"
)
public interface ServiceFeignClient {

    //    register /v1/accounts
    @PostMapping(value = "accounts", consumes = "application/json")
    RegistrationResponse registerUser(@RequestBody RegistrationRequest request);

    //    add new note
    @PostMapping(value = "notes", consumes = "application/json")
    Note addNewNote(@RequestHeader("Authorization") String authorization,
                    @RequestBody CreateNoteRequest request);

    //    get all user notes TEST
    @GetMapping(value = "notes", consumes = "application/json")
    String getUserNotes(@RequestHeader("Authorization") String authorization);

    //       update note
    @PutMapping(value = "notes/{noteId}", consumes = "application/json")
    String updateNote(@PathVariable("noteId") int noteId,
                      @RequestHeader("Authorization") String authorization,
                      @RequestBody UpdateNoteRequest request);

    //       get note by id
    @GetMapping(value = "notes/{noteId}")
    Note getNoteById(@PathVariable("noteId") int noteId,
                     @RequestHeader("Authorization") String authorization);

    //    delete note
    @DeleteMapping(value = "notes/{noteId}")
    void deleteNoteById(@PathVariable("noteId") int noteId,
                        @RequestHeader("Authorization") String authorization);

}
