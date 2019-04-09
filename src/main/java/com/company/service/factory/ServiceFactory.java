package com.company.service.factory;

import com.company.service.*;
import com.company.service.impl.*;

public class ServiceFactory {
    public static IActivityItemService getActivityItemService(){return ActivityItemService.getInstance();}

    public  static IActivityService getActivityService(){return ActivityService.getInstance();}

    public static ILoginRegistrationService getLoginRegistrationService() {
        return LoginRegistrationService.getInstance(); }

    public static IMealItemService getMealItemService(){return MealItemService.getInstance();}

    public static IMealTypeService getMealTypeService(){return MealTypeService.getInstance();}

    public static IMenuService getMenuService(){return MenuService.getInstance();}

    public static  IPageService getPageService(){return PageService.getInstance();}

    public static IUserService getUserService(){return UserService.getInstance(); }

}
