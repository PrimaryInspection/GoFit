package com.company.model.service.impl;

import com.company.model.dao.mapper.DaoFactory;
import com.company.model.dao.mapper.MealTypeDao;
import com.company.model.entity.MealType;
import com.company.model.service.IMealTypeService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class MealTypeService implements IMealTypeService {
    private final static Logger logger = LogManager.getLogger(MealTypeService.class);
    private static MealTypeDao mealTypeDao = DaoFactory.getMealTypeMethods();
    private static MealTypeService instance = new MealTypeService();

    private MealTypeService() {
    }

    public static MealTypeService getInstance() {
        return instance;
    }

    @Override
    public List<MealType> getAll() {
        return mealTypeDao.getAll();
    }
}
