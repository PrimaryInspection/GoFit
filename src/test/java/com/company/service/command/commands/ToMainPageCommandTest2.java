package com.company.service.command.commands;

import com.company.utils.ConfigurationManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToMainPageCommandTest2 {

    @Test
    public void execute() {
        String page = ConfigurationManager.getProperty("path.page.main");
        assertEquals(page,"/jsp/pages/main.jsp");
    }
}