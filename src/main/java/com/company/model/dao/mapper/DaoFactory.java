package com.company.model.dao.mapper;


import com.company.model.dao.impl.*;


public abstract class DaoFactory {

    public static ActivityDao getActivityMethods() {
        return new ActivityDaoImpl();
    }

    public static ActivityItemDao getActivityItemMethods() {
        return new ActivityItemDaoImpl();
    }

    public static MealDao getMealMethods() {
        return new MealDaoImpl();
    }

    public static MealItemDao getMealItemMethods() {
        return new MealItemDaoImpl();
    }

    public static MealTypeDao getMealTypeMethods() {
        return new MealTypeDaoImpl();
    }

    public static UserDao getUserMethods() {
        return new UserDaoImpl();
    }

    public static LifestyleDao getLifestyleMethods() {
        return new LifestyleDaoImpl();
    }

}
