package com.company.service.command.commands;

import com.company.utils.UtilManager;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteEntryFromFoodDiaryCommandTest {

    @Test
    public void execute() {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        String parametr = UtilManager.getProperty("request.mealId");
        when(request.getParameter("meal_id")).thenReturn(parametr);
        when(request.getSession()).thenReturn(session);

    }
}