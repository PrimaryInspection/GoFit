package com.company.dao.interfaces;


import com.company.model.ActivityItem;

public interface ActivityItemDao extends CrudDao<ActivityItem> {
    ActivityItem getActivityItemByName (String name);
}
