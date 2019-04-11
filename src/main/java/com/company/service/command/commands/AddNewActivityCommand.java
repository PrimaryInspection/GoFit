package com.company.service.command.commands;

import com.company.model.ActivityItem;
import com.company.model.User;
import com.company.service.IActivityItemService;
import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import com.company.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
