package com.company.model.service.impl;

import com.company.model.dao.mapper.DaoFactory;
import com.company.model.dao.mapper.ActivityDao;
import com.company.model.entity.Activity;
import com.company.model.entity.ActivityToDisplay;
import com.company.model.service.IActivityService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class ActivityService implements IActivityService {
    private final static Logger logger = LogManager.getLogger(ActivityService.class);
    private static ActivityDao activityDao = DaoFactory.getActivityMethods();
    private static ActivityService instance = new ActivityService();

    public ActivityService(){}
    /**
     * Getting singletone instance of ActivityService
     * @return ActivityService
     */
    public static ActivityService getInstance(){return instance;}

    /**
     * Adding new activity  to database
     * @return boolean(true if addition is done)
     */
    @Override
    public boolean addActivityToPage(Activity activity) {
        logger.info("Adding activity with id : " + activity.getId() + " to page");
        return  activityDao.addItem(activity);
    }

    /**
     * Getting all user activities  on a specific date
     * @return List<ActivityToDisplay>
     */
    @Override
    public List<ActivityToDisplay> getUserActivityPage(Integer userId, LocalDate date) {
        return activityDao.getAll(userId,date);
    }

    /**
     * Getting total value(calories) of user's activity
     * @return ActivityToDisplay
     */
    @Override
    public ActivityToDisplay getUserActivityTotals(Integer userId, LocalDate date) {
        logger.info("Getting total user's activities by user id: " + userId);
        return activityDao.getTotals(userId,date);
    }
    /**
     * Deleting user's activity from page
     * @return true(if deleting is done)
     */
    @Override
    public boolean deleteActivityFromPage(int id) {
        logger.info("Deleting user's activity by  id: " + id);
        return activityDao.deleteItem(id);
    }
}
