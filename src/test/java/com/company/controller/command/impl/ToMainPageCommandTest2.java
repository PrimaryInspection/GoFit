package com.company.controller.command.impl;

import com.company.model.utils.ConfigurationManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToMainPageCommandTest2 {

    @Test
    public void execute() {
        String page = ConfigurationManager.getProperty("path.page.main");
        assertEquals(page,"/view/user/main.jsp");
    }
}