package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.ActivityItem;
import com.company.model.entity.User;
import com.company.model.service.IActivityItemService;
import com.company.model.service.IPageService;
import com.company.model.service.factory.ServiceFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.model.utils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddNewActivityCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddNewActivityCommand.class);

    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_CALORIES = "calories";


    private IPageService pageService = ServiceFactory.getPageService();
    private IActivityItemService activityService = ServiceFactory.getActivityItemService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.addActivity");

        ActivityItem newActivity = getActivityFromRequest(request);
        request.setAttribute("newActivity", newActivity);


        if (activityService.checkIsActivityExist(newActivity.getName())) {
            logger.info("Such activity already exists!");
            request.setAttribute("errorActivityExistMessage", MessageManager.getProperty("message.activityexist"));
            return page;
        }

        if (activityService.addActivity(newActivity)) {
            session.setAttribute("successAddActivityMessage", MessageManager.getProperty("message.addnewactivitysuccess"));
            pageService.updateMainPageData(session, ((User) session.getAttribute("user")).getUserId());
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            session.setAttribute("errorAddActivityMessage", MessageManager.getProperty("message.addnewactivityerror"));
            page = ConfigurationManager.getProperty("path.page.addProduct");
        }


        return page;
    }

    private ActivityItem getActivityFromRequest(HttpServletRequest request) {
        ActivityItem activity = new ActivityItem(
                request.getParameter(PARAM_NAME_NAME),
                Integer.valueOf(request.getParameter(PARAM_NAME_CALORIES))
        );
        return activity;
    }
}
