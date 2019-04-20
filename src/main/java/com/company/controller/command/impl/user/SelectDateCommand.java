package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.User;
import com.company.model.service.IPageService;
import com.company.model.service.factory.ServiceFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.model.utils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class SelectDateCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SelectDateCommand.class);
    private static final String PARAM_NAME_SELECTED_DATE = "chosenDate";
    private IPageService pageService = ServiceFactory.getPageService();

    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        session.setAttribute("chosenDateSession", LocalDate.parse(
                request.getParameter(PARAM_NAME_SELECTED_DATE).toString()));

        pageService.updateMainPageData(session, ((User)session.getAttribute("user")).getUserId());

        return page;
    }

}
