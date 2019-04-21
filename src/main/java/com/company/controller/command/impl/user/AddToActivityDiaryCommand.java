package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.Activity;
import com.company.model.entity.User;
import com.company.model.service.IActivityService;
import com.company.model.service.IPageService;
import com.company.model.service.factory.ServiceFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.model.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AddToActivityDiaryCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddToActivityDiaryCommand.class);
    private static final String SESSION_ATTR_NAME_USER = UtilManager.getProperty("session.user");
    private static final String REQUEST_PARAM_ACTIVITY_ID = UtilManager.getProperty("request.activityId");
    private static final String REQUEST_PARAM_TIME_SPENT = UtilManager.getProperty("request.timeSpent");
    private static final String SESSION_ATTR_NAME_DATE = UtilManager.getProperty("session.chosenDate");
    private IPageService pageService = ServiceFactory.getPageService();
    private IActivityService activityDiaryService = ServiceFactory.getActivityService();

    /**
     * Adding user's daily activity to diary
     *
     * @return page path
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        Activity activityEntry = new Activity(
                Integer.parseInt(request.getParameter(REQUEST_PARAM_ACTIVITY_ID)),
                ((User) session.getAttribute(SESSION_ATTR_NAME_USER)).getUserId(),
                Integer.parseInt(request.getParameter(REQUEST_PARAM_TIME_SPENT)),
                (LocalDate) session.getAttribute(SESSION_ATTR_NAME_DATE)
        );
        logger.info("Activity entry to add: " + activityEntry);

        if (activityDiaryService.addActivityToPage(activityEntry)) {
            logger.info("Activity successfully added to diary!");
            pageService.updateMainPageData(session, ((User) session.getAttribute("user")).getUserId());
        } else {
            logger.info("Activity adding error!");
            session.setAttribute("errorAddActivityToDiaryMessage", MessageManager.getProperty("message.addactivityerror"));
        }
        return page;
    }

}
