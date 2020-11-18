package com.playtika.automation.school.test.framework.action;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.ServiceFeignClient;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;

@AllArgsConstructor
public class ServiceActions {
    private final ServiceFeignClient serviceFeignClient;

    @Step("Register")
    public RegistrationResponse getRegistration(RegistrationRequest request) {

        return serviceFeignClient.registerUser(request);
        }

    @Step("Create new note")
    public CreateNoteResponse createNote(String token, CreateNoteRequest request){
        return serviceFeignClient.addNewNote(token, request);
    }

/*    @Step("Get all user notes")
    public GetUserNotesResponse getUserNotes(String token){
        return serviceFeignClient.getUserNotes(token);
    }*/
    @Step("Get all user notes")
    public String getUserNotes(String token){
        return serviceFeignClient.getUserNotes(token);
    }

    @Step("Update note")
    public String updateNote(int noteId, String token, UpdateNoteRequest request) {
        return serviceFeignClient.updateNote(noteId, token, request);
    }

}
