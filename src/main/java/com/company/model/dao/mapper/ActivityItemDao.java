package com.company.model.dao.mapper;


import com.company.model.entity.ActivityItem;

public interface ActivityItemDao extends CrudDao<ActivityItem> {
    ActivityItem getActivityItemByName (String name);
}
