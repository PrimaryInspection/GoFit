package com.company.dao.impl;

import com.company.dao.interfaces.ActivityDao;
import com.company.model.Activity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class ActivityDaoImpl extends CrudDaoImpl<Activity> implements ActivityDao{


    @Override
    public List<Activity> getAll(Integer userId, LocalDate chosenDate) {

    }

    @Override
    public Activity getTotals(Integer userId, LocalDate chosenDate) {

    }
}
