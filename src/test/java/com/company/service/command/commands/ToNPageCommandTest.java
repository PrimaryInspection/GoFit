package com.company.service.command.commands;
import com.company.utils.ConfigurationManager;
import org.junit.Test;

import static org.junit.Assert.*;


public class ToNPageCommandTest {

    @Test
    public void execute() {
        String page = ConfigurationManager.getProperty("path.page.admin");
        assertEquals(page,"/jsp/pages/admin/adminPage.jsp");
    }
}