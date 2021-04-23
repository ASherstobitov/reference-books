package com.company.referencebooks.service;

import com.company.referencebooks.entity.State;
import org.springframework.stereotype.Service;

@Service(CurrentDocumentStateService.NAME)
public class CurrentDocumentStateServiceBean implements CurrentDocumentStateService {


    public State setCurrentState(String procTaskName) {
        return getState(procTaskName);
    }

    private State getState(String nameState) {
        State state = null;

        switch (nameState) {
            case "Согласование (менеджер)":
            case "Согласующие":
                state = State.APPROVAL;
                break;
            case "Доработка (инициатор)":
                state = State.REWORKING;
                break;
            case "Подписант":
                state = State.SIGNING;
                break;
            case "Регистрация (инициатор)":
                state = State.REGISTRATION;
                break;
        }
        return state;
    }

}