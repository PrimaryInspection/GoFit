package com.company.controller.command.factory;

import com.company.controller.command.impl.ActionCommand;
import com.company.controller.command.impl.CommandsEnum;
import com.company.controller.command.impl.defaultCommand.EmptyCommand;
import com.company.model.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFactory {
    private static final Logger logger = LogManager.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request , HttpServletResponse response){
        ActionCommand currentCommand = new EmptyCommand();
        String action = request.getParameter("command");
        logger.info("Request attribute 'command'='" + action+"'");
        if(action == null  || action.isEmpty()){
            logger.info("Command is null or empty.");
            return currentCommand;
        }
        try {
            CommandsEnum currentEnum = CommandsEnum.valueOf(action.toUpperCase());
            currentCommand = currentEnum.getCurrentCommand();
        }catch (IllegalArgumentException e){
            request.setAttribute("wrongAction",action + MessageManager.getProperty("message.wrongaction"));
        }
        return currentCommand;
    }
}
