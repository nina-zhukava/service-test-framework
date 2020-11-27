package com.playtika.automation.school.test.framework;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
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
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = {
        AuthConfiguration.class,
        ServiceConfiguration.class
})
class ServiceTest {
    @Autowired
    private AuthActions authActions;

    @Autowired
    private ServiceActions serviceActions;

    private static final String CONTENT_ONE = "Lorem ipsum dolor sit amet";
    private static final String CONTENT_TWO = "Ut enim ad minim veniam";

    @SneakyThrows
    @Test
    void serviceTest() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();

        serviceActions.getRegistration(registrationRequest);
        AuthResponse authResponse = authActions.getAuthentication(email, password);
        String authToken = "Bearer " + authResponse.getAccessToken();

        CreateNoteRequest createNoteRequest = new CreateNoteRequest(CONTENT_ONE);
        Note firstNote = serviceActions.createNote(authToken, createNoteRequest);

        String notesFirstVersion = serviceActions.getUserNotes(authToken); //find a way tp get userNotesResponse here instead of String
        ObjectMapper mapper = new ObjectMapper();//        TODO костыль, переделать
        List<Note> notesFirstVersionList = mapper.reader().forType(new TypeReference<List<Note>>() {
        }).readValue(notesFirstVersion);
        assertThat(notesFirstVersionList.size()).isEqualTo(1);

        serviceActions.createNote(authToken, createNoteRequest);
        List<Note> notesSecondVersionList = mapper.reader().forType(new TypeReference<List<Note>>() {
        }).readValue(serviceActions.getUserNotes(authToken));
        assertThat(notesSecondVersionList.size()).isEqualTo(2);
        assertThat(notesSecondVersionList.get(1).getId()).isEqualTo(firstNote.getId());

        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest(CONTENT_TWO, firstNote.getVersion());
        serviceActions.updateNote(firstNote.getId(), authToken, updateNoteRequest);

        String notesThirdVersion = serviceActions.getUserNotes(authToken);
        List<Note> notesThirdVersionList = mapper.reader().forType(new TypeReference<List<Note>>() {
        }).readValue(notesThirdVersion);

        Note updatedNote = notesThirdVersionList.stream()
                                                .filter(note -> note.getId().equals(firstNote.getId()))
                                                .findFirst()
                                                .orElseThrow(() -> new RuntimeException("updated note is not in the list, note id: " + firstNote.getId()));

        assertThat(updatedNote.getId()).isEqualTo(firstNote.getId());
        assertThat(serviceActions.getNoteById(firstNote.getId(), authToken).getVersion()).isEqualTo(1);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedNote.getContent()).isEqualTo(CONTENT_TWO);
            softly.assertThat(updatedNote.getCreatedAt()).isEqualTo(firstNote.getCreatedAt());
            softly.assertThat(updatedNote.getModifiedAt()).isNotEqualTo(firstNote.getModifiedAt());
        });

        serviceActions.deleteNoteById(updatedNote.getId(), authToken);
        String notesForthVersion = serviceActions.getUserNotes(authToken);
        List<Note> notesForthVersionList = mapper.reader().forType(new TypeReference<List<Note>>() {
        }).readValue(notesForthVersion);
        notesForthVersionList.forEach(System.out::println);
        assertThat(notesForthVersionList.size()).isEqualTo(1);
        assertThat(notesForthVersionList.get(0).getId()).isNotEqualTo(updatedNote.getId());

        assertThatThrownBy(() -> serviceActions.getNoteById(updatedNote.getId(), authToken)).hasMessageContaining(
                "Note with id [" + updatedNote.getId() + "] wasn't found");
        serviceActions.deleteNoteById(notesSecondVersionList.get(0).getId(), authToken);

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
    Check that update note has the same id as first note. Check that version was incremented. Check that content was update according to text from update
    step. Check that creation date is equal to first note creation date. Check that modification date is not the same, as in first note.
    Delete first note.
    Get list of notes and assert it has size equal to one and it doesn't contain updated note.
    Try to get deleted note and assert that method throws error which contains message: "Note with id [{your note id}] wasn't found"
    delete second note.*/
