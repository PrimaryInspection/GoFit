package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.Meal;
import com.company.model.entity.User;
import com.company.model.service.IMenuService;
import com.company.model.service.IPageService;
import com.company.model.service.factory.ServiceFactory;
import com.company.model.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AddMealCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddMealCommand.class);

    private static final String SESSION_ATTR_NAME_USER = UtilManager.getProperty("session.user");
    private static final String SESSION_ATTR_NAME_DATE = UtilManager.getProperty("session.chosenDate");
    private static final String REQUEST_PARAM_PRODUCT_ID = UtilManager.getProperty("request.mealItemId");
    private static final String REQUEST_PARAM_WEIGHT = UtilManager.getProperty("request.weight");
    private static final String REQUEST_PARAM_MEAL_TYPE_ID = UtilManager.getProperty("request.mealTypeId");

    private IPageService pageService = ServiceFactory.getPageService();
    private IMenuService menuService = ServiceFactory.getMenuService();

    /**
     * Adding user's eaten meal to diary
     * @return page path
     */
    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        logger.info("Chosen meal type: '" + request.getParameter(REQUEST_PARAM_MEAL_TYPE_ID));


        Meal mealEntry = new Meal(
                ((User) session.getAttribute(SESSION_ATTR_NAME_USER)).getUserId(),
                Integer.parseInt(request.getParameter(REQUEST_PARAM_MEAL_TYPE_ID)),
                Integer.parseInt(request.getParameter(REQUEST_PARAM_PRODUCT_ID)),
                Integer.parseInt(request.getParameter(REQUEST_PARAM_WEIGHT)),
                (LocalDate) session.getAttribute(SESSION_ATTR_NAME_DATE)
        );
        logger.info("Meal entry to add: " + mealEntry);


        if (menuService.addMeal(mealEntry)) {
            logger.info("Meal successfully added!");
            pageService.updateMainPageData(session, mealEntry.getUserId());
        } else {
            logger.info("Meal adding error!");
            session.setAttribute("errorAddMealMessage", MessageManager.getProperty("message.addmealerror"));
        }

        return page;
    }

}
