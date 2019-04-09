package com.company.service.impl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.ActivityDao;
import com.company.model.Activity;
import com.company.model.ActivityToDisplay;
import com.company.service.IActivityService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class ActivityService implements IActivityService {
    private final static Logger logger = LogManager.getLogger(ActivityService.class);
    private static ActivityDao activityDao = DaoFactory.getActivityMethods();
    private static ActivityService instance = new ActivityService();

    public ActivityService(){}

    public static ActivityService getInstance(){return instance;}

    @Override
    public boolean addActivityToPage(Activity activity) {
        logger.info("Adding activity with id : " + activity.getId() + " to page");
        return  activityDao.addItem(activity);
    }

    @Override
    public List<ActivityToDisplay> getUserActivityPage(Integer userId, LocalDate date) {
        return activityDao.getAll(userId,date);
    }

    @Override
    public ActivityToDisplay getUserActivityTotals(Integer userId, LocalDate date) {
        logger.info("Getting total user's activities by user id: " + userId);
        return activityDao.getTotals(userId,date);
    }

    @Override
    public boolean deleteActivityFromPage(int id) {
        logger.info("Deleting user's activity by  id: " + id);
        return activityDao.deleteItem(id);
    }
}
