package com.company.service.impl;

import com.company.model.MealToDisplay;
import com.company.model.MealType;
import com.company.model.User;
import com.company.service.*;
import com.company.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

public class PageService implements IPageService {
    private static final Logger logger = LogManager.getLogger(PageService.class);
    private static final String PARAM_NAME_LOGIN = "pageNumber";
    private static PageService instance = new PageService();
    private IUserService userService = ServiceFactory.getUserService();
    private IMealItemService mealItemService = ServiceFactory.getMealItemService();
    private IMenuService menuService = ServiceFactory.getMenuService();
    private IMealTypeService mealTypeService = ServiceFactory.getMealTypeService();
    private IActivityService activityService = ServiceFactory.getActivityService();
    private IActivityItemService activityItemService = ServiceFactory.getActivityItemService();
    private boolean redirect = false;

    private PageService() {
    }

    public static PageService getInstance() {
        return instance;
    }

    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    @Override
    public void updateMainPageData(HttpSession session, int userId) {
    LocalDate chosenDate = getChosenDate(session);

    updateUserData(session,userId);
    updateFoodTab(session,userId, chosenDate);
    updateActivityTab(session,userId,chosenDate);
    updateBodyStatsTab(session);

    }

    @Override
    public void updateRegistrationPageData(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("lifestyles", userService.getLifestyles());
        session.setAttribute("maxBirth", LocalDate.now());
        session.setAttribute("minBirth", LocalDate.parse("1900-01-01"));
    }

    @Override
    public void updateAdminPageData(HttpServletRequest request) {
        int size = userService.getUsersCount();
        int offset = 0;
        int limit = 5;
        int pages = 1;

        logger.debug("requested page number: " + request.getParameter(PARAM_NAME_LOGIN));
        if (request.getParameter(PARAM_NAME_LOGIN) != null) {
            logger.info("This is updateAdminPage()");
            int requestedPage = Integer.valueOf(request.getParameter(PARAM_NAME_LOGIN));
            offset = (requestedPage - 1) * limit;
            List<User> users = userService.getUsers(limit, offset);
            request.setAttribute("users", users);
        }



        /**
         * finding out how many pagination buttons (pages) shall be shown based on number of users (size) and
         * number of entries to be shown on one page (limit).
         */
        if (size > limit) {
            pages = size / limit;
            if (size % limit > 0) {
                pages++;
            }
        }
        logger.debug("Total number of pages: " + pages);
        request.setAttribute("pages", pages);

        int currentPage = offset / limit + 1;
        logger.debug("Current page number: " + currentPage);
        request.setAttribute("currentPage", currentPage);

    }

    @Override
    public void updateUserData(HttpSession session, Integer userId) {
        logger.info("This is updateUserData();");
        session.setAttribute("user", userService.getUser(userId));
    }

    private void updateFormulaAndGoals(HttpSession session, int userId, LocalDate chosenDate) {
        User user = userService.getUser(userId);
        // FORMULA
        Integer remaining = user.getCalories_norm() - menuService.getUserMealTotal(userId, chosenDate).getCalories() +
                activityService.getUserActivityTotals(user.getUserId(), chosenDate).getCalories();
        session.setAttribute("remaining", remaining);
        Integer goal=user.getWeight() - user.getWeightGoal();

//        GOALS
        session.setAttribute("kgToGoal", goal);
    }


    private LocalDate getChosenDate(HttpSession session){
        LocalDate chosenDate = (LocalDate) session.getAttribute("chosenDateSession");
        if(chosenDate == null) {
            chosenDate=LocalDate.now();
            session.setAttribute("chosenDateSession" , chosenDate);
            logger.info("Setting chosenDate to session: " + session.getAttribute("chosenDateSession"));
        }
        return chosenDate;
    }

    private void updateFoodTab(HttpSession session, int id, LocalDate date){

        List<MealToDisplay> userMealToDisplay = menuService.getUserMenu(id, date);

        /**
         * gets list of current meal types from db and writes them into session
         */
        List<MealType> mealTypes = mealTypeService.getAll();

        /**
         * gets list of current products from db and writes them into session
         */

        Map<String , MealToDisplay> totalsByMealType = makeItemsMap(id,date,mealTypes);
        Map<String , List<MealToDisplay>> mealsSplittedByType = makeMealMap(mealTypes,userMealToDisplay);

        session.setAttribute("mealTypes", mealTypes);
        session.setAttribute("products", mealItemService.getAll());
        session.setAttribute("meals", mealsSplittedByType);
        session.setAttribute("totalsByMealType", totalsByMealType);
        session.setAttribute("totalDayFoodWeight", menuService.getTotalWeight(userMealToDisplay));
        session.setAttribute("totalDayCalories", menuService.getTotalCalories(userMealToDisplay));
        session.setAttribute("totalDayProteins", menuService.getTotalProteins(userMealToDisplay));
        session.setAttribute("totalDayFat", menuService.getTotalFat(userMealToDisplay));
        session.setAttribute("totalDayCarbs", menuService.getTotalCarbs(userMealToDisplay));

    }

    private void updateBodyStatsTab(HttpSession session){
        //        BODY STATS tab
        session.setAttribute("lifestyles", userService.getLifestyles());
        // will be used in jsp to check that birthday is not in future
        session.setAttribute("currentDate", LocalDate.now());

    }

    private void updateActivityTab(HttpSession session , int userId , LocalDate date){
        session.setAttribute("activities" , activityItemService.getAll());
        session.setAttribute("activitiesList" , activityService.getUserActivityPage(userId,date));
        session.setAttribute("activitiesListTotals" , activityService.getUserActivityTotals(userId,date));
    }

    private Map<String, MealToDisplay> makeItemsMap(int userId, LocalDate date, List<MealType> mealTypes) {
        Map<String, MealToDisplay> map = new HashMap<>();
        for (MealType types : mealTypes) {
            map.put(types.getName(), menuService.getTotalsByMealType(userId, date, types.getMealTypeId()));
        }
        return map;
    }

    /**
     * Makes map with key = MealType, value = list of meals within MealType
     * to be displayed on jsp page.
     *
     * @param mealTypes
     * @param meals - list of all meals of certain user on certain date
     * @return
     */

    private Map<String, List<MealToDisplay>> makeMealMap(List<MealType> mealTypes, List<MealToDisplay> meals) {
        Map<String, List<MealToDisplay>> map = new LinkedHashMap<>();
        for (MealType type : mealTypes
                ) {
            map.put(type.getName(), new ArrayList<>());
        }

        for (MealToDisplay meal : meals) {
            map.get(meal.getMealType()).add(meal);
        }
        return map;
    }
}
