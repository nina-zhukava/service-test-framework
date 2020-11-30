package com.playtika.automation.school.test.framework;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.action.ServiceActions;
import com.playtika.automation.school.test.framework.configuration.AuthConfiguration;
import com.playtika.automation.school.test.framework.configuration.ServiceConfiguration;
import com.playtika.automation.school.test.framework.pojo.Note;
import com.playtika.automation.school.test.framework.pojo.User;
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
    private String authToken;

    @Autowired
    private AuthActions authActions;

    @Autowired
    private ServiceActions serviceActions;

    @BeforeEach
    void setUp() {
        User user = serviceActions.getRegistration();
        AuthResponse authResponse = authActions.getAuthentication(user.getEmail(), user.getPassword());
        authToken = authResponse.getTokenType() + authResponse.getAccessToken();
    }

    @Test
    void serviceTest() {
        Note firstNote = serviceActions.createNote(authToken, CONTENT_ONE);

        List<Note> notesFirstVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesFirstVersion).hasSize(1);

        serviceActions.createNote(authToken, CONTENT_ONE);
        List<Note> notesSecondVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesSecondVersion).hasSize(2);
        assertThat(notesSecondVersion.get(1).getId()).isEqualTo(firstNote.getId());
        assertThat(firstNote).isEqualTo(serviceActions.getNoteById(authToken, firstNote.getId()));

        serviceActions.updateNote(authToken, firstNote.getId(), CONTENT_TWO, firstNote.getVersion());

        List<Note> notesThirdVersion = serviceActions.getUserNotes(authToken);

        Note updatedNote = notesThirdVersion.stream()
                                            .filter(note -> firstNote.getId().equals(note.getId()))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException("Updated note is not in the list, note id: " + firstNote.getId()));
        assertThat(updatedNote.getId()).isEqualTo(firstNote.getId());
        assertThat(serviceActions.getNoteById(authToken, firstNote.getId()).getVersion()).isEqualTo(1);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedNote.getContent()).isEqualTo(CONTENT_TWO);
            softly.assertThat(updatedNote.getCreatedAt()).isEqualTo(firstNote.getCreatedAt());
            softly.assertThat(updatedNote.getModifiedAt()).isNotEqualTo(firstNote.getModifiedAt());
            softly.assertThat(updatedNote.getVersion()).isEqualTo(firstNote.getVersion() + 1);
        });

        serviceActions.deleteNote(authToken, updatedNote.getId());
        List<Note> notesForthVersion = serviceActions.getUserNotes(authToken);
        assertThat(notesForthVersion).hasSize(1);
        assertThat(notesForthVersion).doesNotContain(updatedNote);

        assertThatThrownBy(() -> serviceActions.getNoteById(authToken, updatedNote.getId())).hasMessageContaining(
                "Note with id [" + updatedNote.getId() + "] wasn't found");
    }

    @AfterEach
    void tearDown() {
        List<Note> notesRemained = serviceActions.getUserNotes(authToken);
        notesRemained.forEach(note -> serviceActions.deleteNote(authToken, note.getId()));
    }
}