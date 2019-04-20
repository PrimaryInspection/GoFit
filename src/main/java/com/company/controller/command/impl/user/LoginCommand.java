package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.service.ILoginRegistrationService;
import com.company.model.service.IPageService;
import com.company.model.service.IUserService;
import com.company.model.service.factory.ServiceFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.model.utils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private final static String PARAM_NAME_LOGIN="login";
    private final static String PARAM_NAME_PASSWORD="password";

    private IUserService userService = ServiceFactory.getUserService();
    private ILoginRegistrationService logRegService=ServiceFactory.getLoginRegistrationService();
    private IPageService pageService = ServiceFactory.getPageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");
        HttpSession session = request.getSession();

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        request.setAttribute("login",login);
        request.setAttribute("password",password);

        if (logRegService.checkLogin(login,password)){
            logger.info("Login and password checking is OK!");
            pageService.updateMainPageData(session,userService.getUser(login).getUserId());
            //            pageService.setRedirect(true);
            page=ConfigurationManager.getProperty("path.page.main");
        }else {
            request.setAttribute("errorLoginPassMessage" , MessageManager.getProperty("message.loginerror"));
        }
        return page;
    }
}