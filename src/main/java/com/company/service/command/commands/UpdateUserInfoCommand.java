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
import java.time.LocalDate;

public class UpdateUserInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UpdateUserInfoCommand.class);


    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    private static final String PARAM_NAME_WEIGHT = "weight";
    private static final String PARAM_NAME_GOAL_WEIGHT = "goalWeight";
    private static final String PARAM_NAME_HEIGHT = "height";
    private static final String PARAM_NAME_GENDER = "genderId";
    private static final String PARAM_NAME_LIFESTYLE = "lifestyleId";

    private IUserService userService = ServiceFactory.getUserService();
    private IPageService pageService = ServiceFactory.getPageService();


    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.main");
        HttpSession session = request.getSession();


        User newUser = updateUser(request, (User) session.getAttribute("user"));
        logger.info("User to be updated: " + newUser);


        if (userService.updateUser(newUser)) {
            request.setAttribute("updateUserSuccessMessage", MessageManager.getProperty("message.updateuserconfirm"));
            pageService.updateMainPageData(request.getSession(), newUser.getUserId());
        } else {
            request.setAttribute("updateUserErrorMessage", MessageManager.getProperty("message.updateusererror"));
        }

        return page;
    }

    private User updateUser(HttpServletRequest request, User user) {
        user.setBirthday(LocalDate.parse(request.getParameter(PARAM_NAME_BIRTHDAY)));
        user.setWeight(Float.valueOf(request.getParameter(PARAM_NAME_WEIGHT)));
        user.setWeightGoal(Float.valueOf(request.getParameter(PARAM_NAME_GOAL_WEIGHT)));
        user.setLifestyle_id(Integer.valueOf(request.getParameter(PARAM_NAME_LIFESTYLE)));
        user.setHeight(Integer.valueOf(request.getParameter(PARAM_NAME_HEIGHT)));

        return user;
    }
}
