package com.company.service.command.commands;

import com.company.model.Meal;
import com.company.model.User;
import com.company.service.IMenuService;
import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import com.company.utils.MessageManager;
import com.company.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AddMealCommand implements ActionCommand{
    private static final Logger logger = LogManager.getLogger(AddMealCommand.class);

    private static final String SESSION_ATTR_NAME_USER = UtilManager.getProperty("session.user");
    private static final String SESSION_ATTR_NAME_DATE = UtilManager.getProperty("session.chosenDate");
    private static final String REQUEST_PARAM_PRODUCT_ID = UtilManager.getProperty("request.productId");
    private static final String REQUEST_PARAM_WEIGHT = UtilManager.getProperty("request.weight");
    private static final String REQUEST_PARAM_MEAL_TYPE_ID = UtilManager.getProperty("request.mealTypeId");

    private IPageService pageService = ServiceFactory.getPageService();
    private IMenuService menuService = ServiceFactory.getMenuService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session=request.getSession();
        String page= ConfigurationManager.getProperty("path.page.main");
        logger.info("Chosen meal type: '" + request.getParameter(REQUEST_PARAM_MEAL_TYPE_ID) + "'");
        Meal meal=new Meal(
                ((User) session.getAttribute(SESSION_ATTR_NAME_USER)).getUserId(),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_PRODUCT_ID)),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_WEIGHT)),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_MEAL_TYPE_ID)),
                (LocalDate) session.getAttribute(SESSION_ATTR_NAME_DATE)
        );
        logger.info("Meal to add: " + meal);

    if(menuService.addMeal(meal)){
logger.info("Meal succesfully added");
pageService.updateMainPageData(session,meal.getUserId());
    }
    else {
        logger.info("Meal Adding Error.");
        session.setAttribute("ErrorAddMealMessage", MessageManager.getProperty("message.addmealerror"));

    }
    return page;
    }
}
