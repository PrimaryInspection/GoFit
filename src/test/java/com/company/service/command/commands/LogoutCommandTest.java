package com.company.service.command.commands;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class LogoutCommandTest {
LogoutCommand command = new LogoutCommand();
    @Test
    public void execute() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

       when(request.getSession()).thenReturn(session);
    }
}
