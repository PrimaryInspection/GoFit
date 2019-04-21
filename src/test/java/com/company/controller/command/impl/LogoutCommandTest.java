package com.company.controller.command.impl;

import com.company.controller.command.impl.user.LogoutCommand;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogoutCommandTest {
LogoutCommand command = new LogoutCommand();
    @Test
    public void execute() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
       when(request.getSession()).thenReturn(session);
    }
}
