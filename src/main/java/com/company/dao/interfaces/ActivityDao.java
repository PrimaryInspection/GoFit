package com.company.dao.interfaces;

import com.company.model.Activity;
import com.company.model.ActivityToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface ActivityDao extends CrudDao<Activity>{
    List<ActivityToDisplay> getAll(Integer userId, LocalDate chosenDate);

    ActivityToDisplay getTotals(Integer userId, LocalDate chosenDate);
}
