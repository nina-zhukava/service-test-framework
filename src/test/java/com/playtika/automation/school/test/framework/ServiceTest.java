package com.playtika.automation.school.test.framework;

import java.util.List;

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

        List<Note> notesFirstVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesFirstVersion.size()).isEqualTo(1);

        serviceActions.createNote(authToken, createNoteRequest);
        List<Note> notesSecondVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesSecondVersion.size()).isEqualTo(2);
        assertThat(notesSecondVersion.get(1).getId()).isEqualTo(firstNote.getId());

        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest(CONTENT_TWO, firstNote.getVersion());
        serviceActions.updateNote(firstNote.getId(), authToken, updateNoteRequest);

        List<Note> notesThirdVersion = serviceActions.getUserNotes(authToken);

        Note updatedNote = notesThirdVersion.stream()
                                            .filter(note -> note.getId().equals(firstNote.getId()))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException("Updated note is not in the list, note id: " + firstNote.getId()));
        assertThat(updatedNote.getId()).isEqualTo(firstNote.getId());
        assertThat(serviceActions.getNoteById(firstNote.getId(), authToken).getVersion()).isEqualTo(1);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedNote.getContent()).isEqualTo(CONTENT_TWO);
            softly.assertThat(updatedNote.getCreatedAt()).isEqualTo(firstNote.getCreatedAt());
            softly.assertThat(updatedNote.getModifiedAt()).isNotEqualTo(firstNote.getModifiedAt());
        });

        serviceActions.deleteNoteById(updatedNote.getId(), authToken);
        List<Note> notesForthVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesForthVersion.size()).isEqualTo(1);
        assertThat(notesForthVersion.get(0).getId()).isNotEqualTo(updatedNote.getId());

        assertThatThrownBy(() -> serviceActions.getNoteById(updatedNote.getId(), authToken)).hasMessageContaining(
                "Note with id [" + updatedNote.getId() + "] wasn't found");
        serviceActions.deleteNoteById(notesSecondVersion.get(0).getId(), authToken);
    }
}