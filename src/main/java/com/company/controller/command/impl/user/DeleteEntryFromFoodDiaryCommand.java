package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.User;
import com.company.model.service.IMenuService;
import com.company.model.service.IPageService;
import com.company.model.service.factory.ServiceFactory;
import com.company.model.utils.ConfigurationManager;
import com.company.model.utils.MessageManager;
import com.company.model.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteEntryFromFoodDiaryCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteEntryFromFoodDiaryCommand.class);
    private static final String REQUEST_PARAM_MEAL_ID = UtilManager.getProperty("request.mealId");
    private IPageService pageService = ServiceFactory.getPageService();
    private IMenuService menuService = ServiceFactory.getMenuService();

    /**
     * Deleting chosen user's MealItem
     *
     * @return page path
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        int id = Integer.valueOf(request.getParameter(REQUEST_PARAM_MEAL_ID));

        if (menuService.deleteMealFromPage(id)) {
            logger.info("Meal successfully deleted from diary!");
            pageService.updateMainPageData(session, ((User) session.getAttribute("user")).getUserId());
        } else {
            logger.info("Meal deleting error!");
            session.setAttribute("errorDeleteMealFromDiaryMessage", MessageManager.getProperty("message.deletemealerror"));
        }
        return page;
    }

}
