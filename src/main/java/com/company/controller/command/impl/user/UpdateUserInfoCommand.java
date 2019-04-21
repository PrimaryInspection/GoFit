package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.User;
import com.company.model.service.IPageService;
import com.company.model.service.IUserService;
import com.company.model.service.factory.ServiceFactory;
import com.company.model.utils.*;
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
    private static final String PARAM_NAME_GOAL_WEIGHT = "weightGoal";
    private static final String PARAM_NAME_HEIGHT = "height";
    private static final String PARAM_NAME_LIFESTYLE = "lifestyleId";

    private IUserService userService = ServiceFactory.getUserService();
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * Updating user's body stats information
     * @return page path
     */
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

    /**
     * Getting user parameters from request
     * @return page path
     */
    private User updateUser(HttpServletRequest request, User user) {
        user.setWeight(Integer.parseInt(request.getParameter(PARAM_NAME_WEIGHT)));
        user.setWeightGoal(Integer.parseInt(request.getParameter(PARAM_NAME_GOAL_WEIGHT)));
        user.setBirthday(LocalDate.parse(request.getParameter(PARAM_NAME_BIRTHDAY)));
        user.setLifestyle_id(Integer.parseInt(request.getParameter(PARAM_NAME_LIFESTYLE)));
        user.setHeight(Integer.parseInt(request.getParameter(PARAM_NAME_HEIGHT)));

        return user;
    }
}
