package com.playtika.automation.school.test.framework.action;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.ServiceFeignClient;
import com.playtika.automation.school.test.framework.pojo.Note;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;

@AllArgsConstructor
public class ServiceActions {
    private final ServiceFeignClient serviceFeignClient;

    @Step("Register")
    public void getRegistration(RegistrationRequest request) {
        serviceFeignClient.registerUser(request);
    }

    @Step("Create new note")
    public Note createNote(String token, CreateNoteRequest request) {
        return serviceFeignClient.addNewNote(token, request);
    }

    /*    @Step("Get all user notes")
        public GetUserNotesResponse getUserNotes(String token){
            return serviceFeignClient.getUserNotes(token);
        }*/
    @Step("Get all user notes")
    public String getUserNotes(String token) {
        return serviceFeignClient.getUserNotes(token);
    }

    @Step("Update note")
    public String updateNote(int noteId, String token, UpdateNoteRequest request) {
        return serviceFeignClient.updateNote(noteId, token, request);
    }

    @Step("Get note by id")
    public Note getNoteById(int noteId, String token) {
        return serviceFeignClient.getNoteById(noteId, token);
    }

    @Step("delete note")
    public void deleteNoteById(int noteId, String token) {
        serviceFeignClient.deleteNoteById(noteId, token);
    }

}
