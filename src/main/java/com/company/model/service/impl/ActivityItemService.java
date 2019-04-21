package com.company.model.service.impl;

import com.company.model.dao.mapper.ActivityItemDao;
import com.company.model.dao.mapper.DaoFactory;
import com.company.model.entity.ActivityItem;
import com.company.model.service.IActivityItemService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ActivityItemService implements IActivityItemService {
    public static final Logger logger = LogManager.getLogger(ActivityItemService.class);
    private static ActivityItemDao activityItemDao = DaoFactory.getActivityItemMethods();
    private static ActivityItemService instance = new ActivityItemService();

    private ActivityItemService() {
    }

    /**
     * Getting singletone instance of ActivityItemService
     *
     * @return ActivityItemService
     */
    public static ActivityItemService getInstance() {
        return instance;
    }

    /**
     * Getting all Activityitems from database
     *
     * @return List<ActivityItem>
     */
    @Override
    public List<ActivityItem> getAll() {
        return activityItemDao.getAll();
    }

    /**
     * Checking is activity item does not existing in database
     *
     * @return boolean(true if does not exist)
     */
    @Override
    public boolean checkIsActivityExist(String name) {
        logger.info("Check if activity with such name already exists");
        return activityItemDao.getActivityItemByName(name) != null;
    }

    /**
     * Adding new activity item to database
     *
     * @return boolean(true if addition is done)
     */
    @Override
    public boolean addActivity(ActivityItem activity) {
        logger.info("Adding activity item : " + activity.getName());
        return activityItemDao.addItem(activity);
    }
}
