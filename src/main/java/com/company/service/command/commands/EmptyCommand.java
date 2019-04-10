package com.company.service.command.commands;

import com.company.service.command.ActionCommand;
import com.company.servlet.MainServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);

    public EmptyCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.error("Empty command");
        return null;    }
}
