package com.playtika.automation.school.test.framework.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;

@FeignClient(
        name = "service-feign-client",
//        url = "${test.service.host}"
        url = "http://taschool-notes-service.herokuapp.com"
)
public interface ServiceFeignClient {

//    register /v1/accounts
    @PostMapping(value = "/v1/accounts", consumes = "application/json")
    RegistrationResponse registerUser(@RequestBody RegistrationRequest request);

//    add new note
    @PostMapping(value = "/v1/notes", consumes = "application/json")
    CreateNoteResponse addNewNote(@RequestHeader("Authorization") String authorization,
                                  @RequestBody CreateNoteRequest request);

//    get all user notes TEST
    @GetMapping(value = "/v1/notes", consumes = "application/json")
    String getUserNotes(@RequestHeader("Authorization") String authorization);

//       update note
    @PutMapping(value = "/v1/notes", consumes = "application/json")
    void getCreateNote(@PathVariable("noteId") Integer id,
                       @RequestHeader("Authorization") String authorization,
                       @RequestParam("content") String content,
                       @RequestParam("version") String version);
//    TODO do I need smth from response?

/*//       get note by id
    @GetMapping(value = "/v1/notes/{noteId}")
    GetUserNoteResponse getUserNoteResponse(@PathVariable("noteId") Integer noteId);


//    delete note
    @DeleteMapping(value = "/v1/notes/{noteId}")
    void deleteNote(@PathVariable("noteId") Integer noteId);*/

}
