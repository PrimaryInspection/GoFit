package com.company.dao.factory;


import com.company.dao.impl.*;
import com.company.dao.interfaces.*;


public class DaoFactory {

    public static ActivityDao getActivityMethods(){ return new ActivityDaoImpl(); }

    public static ActivityItemDao getActivityItemMethods(){return new ActivityItemDaoImpl();}

    public static MealDao getMealMethods(){return new MealDaoImpl();}

    public static MealItemDao getMealItemMethods(){return new MealItemDaoImpl();}

    public static MealTypeDao getMealTypeMethods(){return new MealTypeDaoImpl();}

    public static UserDao getUserMethods(){return new UserDaoImpl();}

}
