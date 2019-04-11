package com.company.service.command.commands;

import com.company.model.User;
import com.company.service.IActivityService;
import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import com.company.utils.MessageManager;
import com.company.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
