package com.playtika.automation.school.test.framework;

import java.util.List;

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

    private static final String CONTENT_ONE = "Lorem ipsum dolor sit amet";
    private static final String CONTENT_TWO = "Ut enim ad minim veniam";

    @Autowired
    private AuthActions authActions;

    @Autowired
    private ServiceActions serviceActions;
    //TODO jupiter before each method registr and token save

    @Test
    void serviceTest() {
        RegistrationRequest registrationRequest = new RegistrationRequest(); // вынести как гет регистрейшн из теста
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        serviceActions.getRegistration(registrationRequest);
        AuthResponse authResponse = authActions.getAuthentication(email, password);
        String authToken = authResponse.getTokenType() + authResponse.getAccessToken();

        CreateNoteRequest createNoteRequest = new CreateNoteRequest(CONTENT_ONE); // take away, use createNote
        Note firstNote = serviceActions.createNote(authToken, createNoteRequest);

        List<Note> notesFirstVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesFirstVersion).hasSize(1);

        serviceActions.createNote(authToken, createNoteRequest);
        List<Note> notesSecondVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesSecondVersion).hasSize(2);
        assertThat(notesSecondVersion.get(1).getId()).isEqualTo(firstNote.getId());
        assertThat(firstNote).isEqualTo(serviceActions.getNoteById(firstNote.getId(), authToken));

        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest(CONTENT_TWO, firstNote.getVersion()); // update note method - take away
        serviceActions.updateNote(firstNote.getId(), authToken, updateNoteRequest);

        List<Note> notesThirdVersion = serviceActions.getUserNotes(authToken);

        Note updatedNote = notesThirdVersion.stream()
                                            .filter(note -> firstNote.getId().equals(note.getId()))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException("Updated note is not in the list, note id: " + firstNote.getId()));
        assertThat(updatedNote.getId()).isEqualTo(firstNote.getId());
        assertThat(serviceActions.getNoteById(firstNote.getId(), authToken).getVersion()).isEqualTo(1);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedNote.getContent()).isEqualTo(CONTENT_TWO);
            softly.assertThat(updatedNote.getCreatedAt()).isEqualTo(firstNote.getCreatedAt());
            softly.assertThat(updatedNote.getModifiedAt()).isNotEqualTo(firstNote.getModifiedAt());
            softly.assertThat(updatedNote.getVersion()).isEqualTo(firstNote.getVersion() + 1);
        });

        serviceActions.deleteNote(updatedNote.getId(), authToken);
        List<Note> notesForthVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesForthVersion).hasSize(1);
        assertThat(notesForthVersion).doesNotContain(updatedNote);

        assertThatThrownBy(() -> serviceActions.getNoteById(updatedNote.getId(), authToken)).hasMessageContaining(
                "Note with id [" + updatedNote.getId() + "] wasn't found");
        serviceActions.deleteNote(notesSecondVersion.get(0).getId(), authToken);
    }
    //TODO after each delete last note here
}