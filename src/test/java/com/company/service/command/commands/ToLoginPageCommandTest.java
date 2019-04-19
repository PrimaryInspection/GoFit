package com.company.service.command.commands;

import com.company.utils.ConfigurationManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToLoginPageCommandTest {

    @Test
    public void execute() {
        String page = ConfigurationManager.getProperty("path.page.login");
        assertEquals(page,"/jsp/login.jsp");
    }
}