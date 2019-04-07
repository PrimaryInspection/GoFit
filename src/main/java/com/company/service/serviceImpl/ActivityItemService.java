package com.company.service.serviceImpl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.ActivityItemDao;
import com.company.model.ActivityItem;
import com.company.service.IActivityItemService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ActivityItemService implements IActivityItemService{
    private final static Logger logger= LogManager.getLogger(ActivityItemService.class);
    private static ActivityItemService instance = new ActivityItemService();

    private static ActivityItemDao activityItemDao= DaoFactory.getActivityItemMethods();

    private ActivityItemService(){}

    public static ActivityItemService getInstance() {
        return instance;
    }

    @Override
    public List<ActivityItem> getAll() {
        return activityItemDao.getAll();
    }

    @Override
    public boolean checkActivityExist(String name) {
        logger.info("Check is activity item with name: "+ name + " already exist.");
        return activityItemDao.getActivityItemByName(name) !=null;
    }

    @Override
    public boolean addActivity(ActivityItem newActivityItem) {
        return activityItemDao.addItem(newActivityItem);
    }
}
