package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
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

public class DeleteEntryFromActivityDiaryCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteEntryFromActivityDiaryCommand.class);

    private static final String REQUEST_PARAM_ACTIVITY_ID = UtilManager.getProperty("request.activityId");

    private IPageService pageService = ServiceFactory.getPageService();
    private IActivityService activityDiaryService = ServiceFactory.getActivityService();

    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        int id = Integer.valueOf(request.getParameter(REQUEST_PARAM_ACTIVITY_ID));

        if (activityDiaryService.deleteActivityFromPage(id)) {
            logger.info("Activity successfully deleted from diary!");
            pageService.updateMainPageData(session, ((User)session.getAttribute("user")).getUserId());
        } else {
            logger.info("Activity deleting error!");
            session.setAttribute("errorDeleteActivityFromDiaryMessage", MessageManager.getProperty("message.deleteactivityerror"));
        }

        return page;
    }

}
