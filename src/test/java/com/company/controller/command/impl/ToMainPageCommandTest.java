package com.company.controller.command.impl;

import com.company.controller.command.impl.user.ToMainPageCommand;

import com.company.model.utils.*;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToMainPageCommandTest {
    ToMainPageCommand command = new ToMainPageCommand();
    @Test
    public void execute() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpSession session = mock(HttpSession.class);

        when(session.getAttribute("user")).thenReturn(UtilManager.getProperty("session.user"));


    }
}