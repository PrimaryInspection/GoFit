package com.company.service.command.commands;

import com.company.model.User;
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

public class BlockUnblockUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(BlockUnblockUserCommand.class);


    private static final String PARAM_NAME_USER_ID = "userId";


    private IUserService userService = ServiceFactory.getUserService();
    private IPageService pageService = ServiceFactory.getPageService();

    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.admin");

        int userId = Integer.valueOf(request.getParameter(PARAM_NAME_USER_ID));
        logger.debug("User id=" + userId);

        User userBlocked = userService.getUser(userId);
        logger.debug("User status id =" + userBlocked.getStatus());


        userBlocked.setStatus((userBlocked.getStatus() == 1) ? 9 : 1);
        logger.debug("New user status id:" + userBlocked.getStatus());


        if (userService.blockUnblockUser(userBlocked)) {
            pageService.updateAdminPageData(request);
        } else {
            request.setAttribute("errorBlockUnblockUserMessage", MessageManager.getProperty("message.userblockerror"));
        }

        return page;
    }
}
