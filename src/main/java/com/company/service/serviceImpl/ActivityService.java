package com.company.service.serviceImpl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.ActivityDao;
import com.company.model.Activity;
import com.company.model.ActivityToDisplay;
import com.company.service.IActivityService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class ActivityService implements IActivityService{
    private static final Logger logger= LogManager.getLogger(ActivityService.class);
    private static ActivityService instance=new ActivityService();
    private ActivityDao activityDao= DaoFactory.getActivityMethods();

    private ActivityService() {}

    public static ActivityService getInstance(){return  instance;}

    @Override
    public boolean addActivityToDiary(Activity activity) {
        return activityDao.addItem(activity);
    }

    @Override
    public List<ActivityToDisplay> getUserActivityDiary(Integer userId, LocalDate chosenDate) {
        return activityDao.getAll(userId, chosenDate);
    }

    @Override
    public ActivityToDisplay getUserActivityDiaryTotals(Integer userId, LocalDate chosenDate) {
        return activityDao.getTotals(userId, chosenDate);
    }

    @Override
    public boolean deleteFromActivityDiary(int id) {
        return activityDao.deleteItem(id);
    }
}
