package com.company.dao.factory;


import com.company.dao.impl.*;
import com.company.dao.interfaces.*;


public class DaoFactory {

    public static ActivityDao getActivities(){ return new ActivityDaoImpl(); }

    public static ActivityItemDao getActivityitems(){return new ActivityItemDaoImpl();}

    public static MealDao getMeals(){return new MealDaoImpl();}

    public static MealItemDao getMealItems(){return new MealItemDaoImpl();}

    public static MealTypeDao getMealTypes(){return new MealTypeDaoImpl();}

    public static UserDao getUsers(){return new UserDaoImpl();}

}
