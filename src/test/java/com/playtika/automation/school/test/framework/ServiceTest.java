package com.playtika.automation.school.test.framework;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.action.ServiceActions;
import com.playtika.automation.school.test.framework.configuration.AuthConfiguration;
import com.playtika.automation.school.test.framework.configuration.ServiceConfiguration;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;

@SpringBootTest(classes = {
        AuthConfiguration.class,
        ServiceConfiguration.class
} )
class ServiceTest {
    @Autowired
    private AuthActions authActions;

    @Autowired
    private ServiceActions serviceActions;

    @Test
    void getAuthResponseTest(){
        RegistrationRequest registrationRequest = new RegistrationRequest();
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        RegistrationResponse registrationResponse = serviceActions.getRegistration(registrationRequest);

        AuthResponse authResponse = authActions.getAuthentication(email, password);

        System.out.println(authResponse.getAccessToken());
        System.out.println(registrationResponse.getUserId());
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
