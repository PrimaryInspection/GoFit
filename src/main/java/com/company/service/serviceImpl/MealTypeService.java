package com.company.service.serviceImpl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.MealTypeDao;
import com.company.model.MealType;
import com.company.service.IMealTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MealTypeService implements IMealTypeService{

    private static final Logger logger = LogManager.getLogger(MealTypeService.class);
    private static MealTypeService instance = new MealTypeService();
    private static MealTypeDao mealTypeDao = DaoFactory.getMealTypeMethods();

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