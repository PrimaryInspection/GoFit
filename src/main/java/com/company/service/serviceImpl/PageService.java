package com.company.service.serviceImpl;

import com.company.model.MealToDisplay;
import com.company.model.MealType;
import com.company.model.User;
import com.company.service.*;
import com.company.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

public class PageService implements IPageService {
    private static final Logger logger = LogManager.getLogger(PageService.class);
    private static PageService instance = new PageService();

    private static final String PARAM_NAME_LOGIN = "pageNumber";
    private boolean redirect = false;

    private PageService() {
    }

    public static PageService getInstance() {
        return instance;
    }


    private IUserService userService = ServiceFactory.getUserService();
    private IMealItemService mealItemService = ServiceFactory.getMealItemService();
    private IMenuService menuService = ServiceFactory.getMenuService();
    private IMealTypeService mealTypeService = ServiceFactory.getMealTypeService();
    private IActivityItemService activityItemService = ServiceFactory.getActivityItemService();
    private IActivityService activityService = ServiceFactory.getActivityService();


    private LocalDate getChosenDate(HttpSession session) {
        LocalDate chosenDate = (LocalDate) session.getAttribute("chosenDateSession");
        if (chosenDate == null) {
            chosenDate = LocalDate.now();
            session.setAttribute("chosenDateSession", chosenDate);
            logger.info("Setting chosenDate to session: " + session.getAttribute("chosenDateSession"));
        }
        return chosenDate;
    }

    private void updateGoals(HttpSession session, int userId, LocalDate chosenDate) {
        User user = userService.getUser(userId);

        Integer remaining = user.getCalories_norm() - menuService.getUserFoodTotal(userId, chosenDate).getCalories() +
                activityService.getUserActivityDiaryTotals(user.getUserId(), chosenDate).getCalories();
        Float kgRemaining = user.getWeight() - user.getWeightGoal();

        session.setAttribute("remaining", remaining);
        session.setAttribute("kgforgoal", kgRemaining);
    }


    private void updateActivityTab(HttpSession session, int userId, LocalDate chosenDate) {

        session.setAttribute("activities", activityItemService.getAll());
        session.setAttribute("activitiesList", activityService.getUserActivityDiary(userId, chosenDate));
        session.setAttribute("activitiesListTotals", activityService.getUserActivityDiaryTotals(userId, chosenDate));
    }

    private void updateBodyStatsTab(HttpSession session) {
        //        BODY STATS tab
        User user = new User();
        session.setAttribute("lifestyles", userService.getUser(user.getLifestyle()));
        // will be used in jsp to check that birthday is not in future
        session.setAttribute("currentDate", LocalDate.now());
    }

    @Override
    public void updateMainPageData(HttpSession session, int userId) {
        LocalDate chosenDate = getChosenDate(session);

        updateUser(session, userId);
        updateFoodTab(session, userId, chosenDate);
        updateActivityTab(session, userId, chosenDate);
        updateBodyStatsTab(session);
        updateGoals(session, userId, chosenDate);

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
    public void updateRegistrationPageData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = new User();
        session.setAttribute("lifestyles", userService.getUser(user.getLifestyle()));
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
            int requestedPage = Integer.valueOf(request.getParameter(PARAM_NAME_LOGIN));
            offset = (requestedPage - 1) * limit;
        }

        List<User> users1 = userService.getUsers(limit, offset);
        request.setAttribute("users", users1);

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
    public void updateUser(HttpSession session, Integer userId) {
        session.setAttribute("user", userService.getUser(userId));
    }


    private Map<String, List<MealToDisplay>> makeMap(List<MealType> mealTypes, List<MealToDisplay> meals) {
        Map<String, List<MealToDisplay>> map = new LinkedHashMap<>();

        for (MealType type : mealTypes
                ) {
            map.put(type.getName(), new ArrayList<>());
        }

        for (MealToDisplay meal : meals
                ) {
            map.get(meal.getMealType()).add(meal);
        }
        return map;
    }

    private Map<String, MealToDisplay> makeMap2(int userId, LocalDate chosenDate, List<MealType> mealTypes) {
        Map<String, MealToDisplay> map = new HashMap<>();
        for (MealType type : mealTypes) {
            map.put(type.getName(), menuService.getTotalsByMealType(userId, chosenDate, type.getMealTypeId()));
        }
        return map;
    }



    private void updateFoodTab(HttpSession session, int userId, LocalDate chosenDate) {

        List<MealToDisplay> userMealToDisplay = menuService.getUserMenu(userId, chosenDate);
        /**
         * gets list of current meal types from db and writes them into session
         */
        List<MealType> mealTypes = mealTypeService.getAll();
        /**
         * gets list of current products from db and writes them into session
         */
        Map<String, MealToDisplay> totalsByMealTypeMap = makeMap2(userId, chosenDate, mealTypes);
        Map<String, List<MealToDisplay>> mealsSplittedByType = makeMap(mealTypes, userMealToDisplay);

        session.setAttribute("mealTypes", mealTypes);
        session.setAttribute("mealItems", mealItemService.getAll());
        session.setAttribute("meals", mealsSplittedByType);
        session.setAttribute("totalsByMealType", totalsByMealTypeMap);
        session.setAttribute("totalDayFoodWeight", menuService.getTotalWeight(userMealToDisplay));
        session.setAttribute("totalDayCalories", menuService.getTotalCalories(userMealToDisplay));
        session.setAttribute("totalDayProteins", menuService.getTotalProteins(userMealToDisplay));
        session.setAttribute("totalDayFat", menuService.getTotalFat(userMealToDisplay));
        session.setAttribute("totalDayCarbs", menuService.getTotalCarbs(userMealToDisplay));
    }
}
