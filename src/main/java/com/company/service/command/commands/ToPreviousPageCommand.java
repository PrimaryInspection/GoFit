package com.company.service.command.commands;

import com.company.service.IPageService;
import com.company.service.IUserService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ToPreviousPageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SelectDateCommand.class);

    /**
     * checks session for 'previous page' attribute and returns its value
     * @return previous page
     */
    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        HttpSession session = request.getSession();

        String page = session.getAttribute("previousPage").toString();
        logger.info("Requested 'previous' page: " + page);

        return page;
    }
}
