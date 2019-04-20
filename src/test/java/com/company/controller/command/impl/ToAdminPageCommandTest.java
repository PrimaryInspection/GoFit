package com.company.controller.command.impl;

import com.company.model.utils.ConfigurationManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToAdminPageCommandTest {

    @Test
    public void execute() {
        String page = ConfigurationManager.getProperty("path.page.admin");
        assertEquals(page,"/jsp/pages/admin/adminPage.jsp");
    }
}