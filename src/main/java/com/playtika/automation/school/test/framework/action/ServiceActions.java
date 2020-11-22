package com.playtika.automation.school.test.framework.action;

import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.ServiceFeignClient;
import com.playtika.automation.school.test.framework.pojo.Note;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;

@AllArgsConstructor
public class ServiceActions {
    private final ServiceFeignClient serviceFeignClient;

    public void getRegistration(RegistrationRequest request) {
        serviceFeignClient.registerUser(request);
    }

    public Note createNote(String token, CreateNoteRequest request) {
        return serviceFeignClient.addNewNote(token, request);
    }

    public String getUserNotes(String token) {
        return serviceFeignClient.getUserNotes(token);
    }

    public void updateNote(int noteId, String token, UpdateNoteRequest request) {
        serviceFeignClient.updateNote(noteId, token, request);
    }

    public Note getNoteById(int noteId, String token) {
        return serviceFeignClient.getNoteById(noteId, token);
    }

    public void deleteNoteById(int noteId, String token) {
        serviceFeignClient.deleteNoteById(noteId, token);
    }

}
