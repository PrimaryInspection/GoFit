package com.company.model.service.impl;

import com.company.model.entity.MealToDisplay;
import com.company.model.entity.MealType;
import com.company.model.entity.User;
import com.company.model.service.*;
import com.company.model.service.factory.ServiceFactory;
import com.company.model.transaction.TransactionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
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

        updateUserData(session, userId);
        updateFoodTab(session, userId, chosenDate);
        updateActivityTab(session, userId, chosenDate);
        updateBodyStatsTab(session);
        updateFormulaAndGoals(session, userId, chosenDate);

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
        try {
            User user = userService.getUser(userId);
            Integer remaining = user.getCalories_norm() - menuService.getUserMealTotal(userId, chosenDate).getCalories() +
                    activityService.getUserActivityTotals(user.getUserId(), chosenDate).getCalories();
            session.setAttribute("remaining", remaining);
            session.setAttribute("kgToGoal", user.getWeight() - user.getWeightGoal());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private LocalDate getChosenDate(HttpSession session) {
        LocalDate chosenDate = (LocalDate) session.getAttribute("chosenDateSession");
        if (chosenDate == null) {
            chosenDate = LocalDate.now();
            session.setAttribute("chosenDateSession", chosenDate);
            logger.info("Setting chosenDate to session: " + session.getAttribute("chosenDateSession"));
        }
        return chosenDate;
    }

    private void updateFoodTab(HttpSession session, int userId, LocalDate chosenDate) {
        TransactionManager tm = new TransactionManager();
        if (userService.getUser(userId) != null) {
            try {
                tm.begin();
                List<MealToDisplay> userMealToDisplay = menuService.getUserMenu(userId, chosenDate);
                List<MealType> mealTypes = mealTypeService.getAll();
                if (!mealTypes.isEmpty()) {
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
                    tm.commit();
                }
            } catch (SQLException e) {
                tm.rollback();
                logger.info("Error during updating user's food table");
                e.printStackTrace();
            } finally {
                tm.close();
            }
        }
    }


    private void updateBodyStatsTab(HttpSession session) {
        session.setAttribute("lifestyles", userService.getLifestyles());
        session.setAttribute("currentDate", LocalDate.now());

    }

    private void updateActivityTab(HttpSession session, int userId, LocalDate date) {
        session.setAttribute("activities", activityItemService.getAll());
        session.setAttribute("activitiesList", activityService.getUserActivityPage(userId, date));
        session.setAttribute("activitiesListTotals", activityService.getUserActivityTotals(userId, date));
    }

    /**
     * Makes map with key = MealType, value = list of meals within MealType
     * to be displayed on jsp page.
     *
     * @param mealTypes
     * @param meals     - list of all meals of certain user on certain date
     * @return
     */
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


    /**
     * Makes map with key = MealType.getName(), value =  meals within MealType
     * to be displayed on jsp page.
     *
     * @param userId
     * @param mealTypes - list of all meal types of certain user on certain date
     * @return HashMap map
     */
    private Map<String, MealToDisplay> makeMap2(int userId, LocalDate chosenDate, List<MealType> mealTypes) {
        Map<String, MealToDisplay> map = new HashMap<>();
        for (MealType type : mealTypes) {
            map.put(type.getName(), menuService.getTotalsByMealType(userId, chosenDate, type.getMealTypeId()));
        }
        return map;
    }
}
