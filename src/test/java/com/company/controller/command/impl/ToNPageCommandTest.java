package com.company.controller.command.impl;
import com.company.model.utils.ConfigurationManager;
import org.junit.Test;

import static org.junit.Assert.*;


public class ToNPageCommandTest {

    @Test
    public void execute() {
        String page = ConfigurationManager.getProperty("path.page.admin");
        assertEquals(page,"/view/admin/adminPage.jsp");
    }
}