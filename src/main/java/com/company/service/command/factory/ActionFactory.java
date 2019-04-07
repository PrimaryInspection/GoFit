package com.company.service.command.factory;

import com.company.service.command.ActionCommand;
import com.company.service.command.client.CommandEnum;
import com.company.utils.MessageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final Logger logger = LogManager.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand currentCommand = new EmptyCommand();
        String action = request.getParameter("command");
        logger.info("Request attrubute's 'command= '" + action);
        if (action == null || action.isEmpty()) {
            logger.info("Command is NULL or empty");
            return currentCommand;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            currentCommand = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return currentCommand;
    }
}
