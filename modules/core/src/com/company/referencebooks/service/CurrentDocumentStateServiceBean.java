package com.company.referencebooks.service;

import com.company.referencebooks.entity.State;
import org.springframework.stereotype.Service;

@Service(CurrentDocumentStateService.NAME)
public class CurrentDocumentStateServiceBean implements CurrentDocumentStateService {


    public State setCurrentState(String procTaskName) {
        return getState(procTaskName);
    }
    /**
     * Get a state
     * This method set required state by a process task name
     * */
    private State getState(String nameState) {
        State state = null;
        if (nameState.equals("Согласование (менеджер)") || nameState.equals("Согласование (менеджер)")) {
            state = State.APPROVAL;
        } else if (nameState.equals("Доработка (инициатор)")) {
            state = State.REWORKING;
        } else if (nameState.equals("Подписант")) {
            state = State.SIGNING;
        } else if (nameState.equals("Регистрация (инициатор)")) {
            state = State.REGISTRATION;
        }
        return state;
    }
}