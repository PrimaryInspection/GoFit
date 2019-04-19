package com.company.service.command.commands;

import com.company.model.User;
import com.company.utils.UtilManager;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ToMainPageCommandTest {
    ToMainPageCommand command = new ToMainPageCommand();
    @Test
    public void execute() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpSession session = mock(HttpSession.class);

        when(session.getAttribute("user")).thenReturn(UtilManager.getProperty("session.user"));


    }
}