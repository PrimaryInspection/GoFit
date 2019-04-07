package com.company.service.factory;

import com.company.service.*;
import com.company.service.serviceImpl.*;


public class ServiceFactory {
    public static IUserService getUserService() { return UserService.getInstance(); }

    public static IMealItemService getProductService() {
        return MealItemService.getInstance();
    }

    public static IMenuService getMenuService() { return MenuService.getInstance(); }

    public static ILoginRegistrationService getLoginRegistrationService() { return LoginRegistrationService.getInstance(); }

    public static IMealTypeService getMealTypeService() {
        return MealTypeService.getInstance();
    }

    public static IPageService getPageService() {
        return PageService.getInstance();
    }

    public static IActivityService getActivityService() {
        return ActivityService.getInstance();
    }

    public static IActivityItemService getActivityDiaryService() {
        return ActivityItemService.getInstance();
    }
}
