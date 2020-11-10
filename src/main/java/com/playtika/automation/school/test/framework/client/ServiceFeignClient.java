package com.playtika.automation.school.test.framework.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.pojo.responses.GetAllUserNotesResponse;
import com.playtika.automation.school.test.framework.pojo.responses.GetUserNoteResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;

@FeignClient(
        name = "service-feign-client",
        url = "${test.service.host}"
)
public interface ServiceFeignClient {

//    register /v1/accounts
    @PostMapping(value = "/v1/accounts")
    RegistrationResponse registerUser(@RequestParam("content") CreateNoteRequest content);


    //get all user notes
    @GetMapping(value = "/v1/notes")
    GetAllUserNotesResponse getAllUserNotesResponse();

//    add new note
    @PostMapping(value = "/v1/notes")
    CreateNoteResponse addNewNote(@RequestParam("content") CreateNoteRequest content);


    @GetMapping(value = "/v1/notes/{noteId}")
    GetUserNoteResponse getUserNoteResponse(@PathVariable("noteId") Integer noteId);

//    create note
    @PutMapping(value = "/v1/notes/{noteId}")
    void getCreateNote(@PathVariable("noteId") Integer noteId);

//    delete note
    @DeleteMapping(value = "/v1/notes/{noteId}")
    void deleteNote(@PathVariable("noteId") Integer noteId);

}
