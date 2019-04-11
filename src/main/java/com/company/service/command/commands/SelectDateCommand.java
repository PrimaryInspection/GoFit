package com.company.service.command.commands;

import com.company.model.User;
import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
