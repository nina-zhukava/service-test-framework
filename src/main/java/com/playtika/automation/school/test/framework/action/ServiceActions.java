package com.playtika.automation.school.test.framework.action;

import java.util.List;

import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.ServiceFeignClient;
import com.playtika.automation.school.test.framework.pojo.Note;
import com.playtika.automation.school.test.framework.pojo.User;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;

@AllArgsConstructor
public class ServiceActions {
    private final ServiceFeignClient serviceFeignClient;

    public User getRegistration() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        User user = new User(registrationRequest.getEmail(), registrationRequest.getPassword());
        serviceFeignClient.registerUser(registrationRequest);
        return user;
    }

    public Note createNote(String token, String content) {
        CreateNoteRequest request = new CreateNoteRequest(content);
        return serviceFeignClient.addNewNote(token, request);
    }

    public List<Note> getUserNotes(String token) {
        return serviceFeignClient.getUserNotes(token);
    }

    public void updateNote(String token, int noteId, String content, int version) {
        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest(content, version);
        serviceFeignClient.updateNote(token, noteId, updateNoteRequest);
    }

    public Note getNoteById(String token, int noteId) {
        return serviceFeignClient.getNoteById(token, noteId);
    }

    public void deleteNote(String token, int noteId) {
        serviceFeignClient.deleteNoteById(token, noteId);
    }
}