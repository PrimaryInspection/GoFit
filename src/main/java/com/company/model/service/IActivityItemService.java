package com.company.model.service;


import com.company.model.entity.ActivityItem;

import java.util.List;

public interface IActivityItemService {
    List<ActivityItem> getAll();

    boolean checkIsActivityExist(String name);

    boolean addActivity(ActivityItem activityItem);
}
