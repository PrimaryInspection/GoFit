package com.company.model.dao.mapper;

import com.company.model.entity.Activity;
import com.company.model.entity.ActivityToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface ActivityDao extends CrudDao<Activity>{
    List<ActivityToDisplay> getAll(Integer userId, LocalDate chosenDate);

    ActivityToDisplay getTotals(Integer userId, LocalDate chosenDate);
}
