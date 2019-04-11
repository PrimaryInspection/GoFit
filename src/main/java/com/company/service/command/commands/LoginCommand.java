package com.company.service.command.commands;

import com.company.service.ILoginRegistrationService;
import com.company.service.IPageService;
import com.company.service.IUserService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import com.company.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
