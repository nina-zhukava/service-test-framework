package com.playtika.automation.school.test.framework.action;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.ServiceFeignClient;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;

@AllArgsConstructor
public class ServiceActions {
    private final ServiceFeignClient serviceFeignClient;

    @Step("Register")
    public RegistrationResponse getRegistration(RegistrationRequest request) {

        return serviceFeignClient.registerUser(request);
        }

    }


/*
public CreateNoteResponse createNote(Note content){
        CreateNoteRequest request = CreateNoteRequest
        .builder()
        .content(content.toString())
        .build();
        return serviceFeignClient.addNewNote(request);*/
