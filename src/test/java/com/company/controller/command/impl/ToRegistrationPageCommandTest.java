package com.company.controller.command.impl;

import com.company.model.utils.ConfigurationManager;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToRegistrationPageCommandTest {

    @Test
    public void execute() {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        String page = ConfigurationManager.getProperty("path.page.registration");
        assertEquals(page,"/jsp/registration.jsp");

        when(request.getSession()).thenReturn(session);

    }


}