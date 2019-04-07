package com.company.service;

import com.company.model.ActivityItem;

import java.util.List;

public interface IActivityItemService {

    List<ActivityItem> getAll();

    boolean checkActivityExist(String name);

    boolean addActivity(ActivityItem newActivityItem);
}
