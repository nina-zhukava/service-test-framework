package com.playtika.automation.school.test.framework;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.action.ServiceActions;
import com.playtika.automation.school.test.framework.configuration.AuthConfiguration;
import com.playtika.automation.school.test.framework.configuration.ServiceConfiguration;
import com.playtika.automation.school.test.framework.pojo.Note;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;
import com.playtika.automation.school.test.framework.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
        AuthConfiguration.class,
        ServiceConfiguration.class
} )
class ServiceTest {
    @Autowired
    private AuthActions authActions;

    @Autowired
    private ServiceActions serviceActions;

    private static final String CONTENT_ONE = "Lorem ipsum dolor sit amet, consectetur elit, sed do eiusmod tempor incididunt ut labore et dolore magna";
    private static final String CONTENT_TWO = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    @SneakyThrows
    @Test
    void serviceTest(){
//        Generate random email and password.
        RegistrationRequest registrationRequest = new RegistrationRequest();
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();

//        Register user
        RegistrationResponse registrationResponse = serviceActions.getRegistration(registrationRequest);

//        Authenticate and get token
        AuthResponse authResponse = authActions.getAuthentication(email, password);
        String authToken = "Bearer " + authResponse.getAccessToken();

//        create a note with any content
        CreateNoteRequest createNoteRequest = new CreateNoteRequest(CONTENT_ONE);
        CreateNoteResponse createFirstNoteResponse = serviceActions.createNote(authToken, createNoteRequest);

        String userNotesResponse = serviceActions.getUserNotes(authToken); //find a way tp get userNotesResponse here instead of String

        ObjectMapper mapper = new ObjectMapper();
        List<Note> notes = mapper.reader().forType(new TypeReference<List<Note>>() {
        }).readValue(userNotesResponse);
//        TODO костыль, переделать
        assertThat(notes.size()).isEqualTo(1);

        serviceActions.createNote(authToken, createNoteRequest);
        String userNotesSecondResponse = serviceActions.getUserNotes(authToken);
        List<Note> notesUpdated = mapper.reader().forType(new TypeReference<List<Note>>() {
        }).readValue(userNotesSecondResponse);
        assertThat(notesUpdated.size()).isEqualTo(2);
//        Get first note by id and assert it's the same as you've created.
        assertThat(notesUpdated.get(1).getId()).isEqualTo(createFirstNoteResponse.getNoteId());

        serviceActions.updateNote(authToken, notesUpdated.get(1).getId(), CONTENT_TWO, notesUpdated.get(1).getVersion());//debug needed - doesn't update
        String userNotesThirdResponse = serviceActions.getUserNotes(authToken);
        System.out.println(userNotesThirdResponse);

    }

}
/*
    Generate random email and password.
    Register user
    Authenticate and get token
    create a note with any content
    Get list of notes and assert it has size equals to one.
    create second note
    Get list of notes and assert it has size has grown.
    Get first note by id and assert it's the same as you've created.

        Update first note with any new content
        Get list of notes. Use stream to filter list by id of note and get updated one.
        Check that update note has the same id as first note. Check that version was incremented. Check that content was update according to text from update step. Check that creation date is equal to first note creation date. Check that modification date is not the same, as in first note.
        Delete first note.
        Get list of notes and assert it has size equal to one and it doesn't contain updated note.
        Try to get deleted note and assert that method throws error which contains message: "Note with id [{your note id}] wasn't found"
        delete second note.*/
