package com.playtika.automation.school.test.framework.action;

import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.client.ServiceFeignClient;
import com.playtika.automation.school.test.framework.pojo.Note;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.responses.CreateNoteResponse;

@AllArgsConstructor
public class ServiceActions {
    private final ServiceFeignClient serviceFeignClient;

    public CreateNoteResponse createNote(Note content){
        CreateNoteRequest request = CreateNoteRequest
                .builder()
                .content(content.toString())
                .build();
        return serviceFeignClient.addNewNote(request);
    }


}
/*
@AllArgsConstructor
public class BombStrikeBombBalanceActions {

    private final BombStrikeBombBalanceFeignClient bombStrikeBombBalanceFeignClient;

    @Step("Get bomb balance")
    @com.epam.reportportal.annotations.Step("Get bomb balance")
    public GetBombBalanceResponse getBombBalance(PkUser user) {
        BaseServiceRequest request = new BaseServiceRequest(user);
        return bombStrikeBombBalanceFeignClient.getBombBalance(request);
    }
}*/
