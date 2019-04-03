package com.company.dao.interfaces;

import com.company.model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface ActivityDao extends CrudDao<Activity>{
    List<Activity> getAll(Integer userId, LocalDate chosenDate);

    Activity getTotals(Integer userId, LocalDate chosenDate);
}
