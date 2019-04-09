package com.company.service.impl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.ActivityItemDao;
import com.company.model.ActivityItem;
import com.company.service.IActivityItemService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ActivityItemService implements IActivityItemService {
    public static final Logger logger = LogManager.getLogger(ActivityItemService.class);
    private static ActivityItemDao activityItemDao= DaoFactory.getActivityItemMethods();
    private static ActivityItemService instance = new ActivityItemService();

    private ActivityItemService(){}

    public static ActivityItemService getInstance(){
        return instance;
    }

    @Override
    public List<ActivityItem> getAll() {
        return activityItemDao.getAll();
    }

    @Override
    public boolean checkIsActivityExist(String name) {
        logger.info("Check if activity with such name already exists");
        return activityItemDao.getActivityItemByName(name) !=null;
    }

    @Override
    public boolean addActivity(ActivityItem activity) {
        logger.info("Adding activity item : " + activity.getName());
       return activityItemDao.addItem(activity);
    }
}
