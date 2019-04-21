package com.company.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
    /**
     * Main interface for commands
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
