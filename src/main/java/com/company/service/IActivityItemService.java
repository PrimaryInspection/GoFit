package com.company.service;


import com.company.model.ActivityItem;

import java.util.List;

public interface IActivityItemService {
    List<ActivityItem> getAll();

    boolean checkIsActivityExist(String name);

    boolean addActivity(ActivityItem activityItem);
}
