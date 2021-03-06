package com.company.controller.command.impl.user;

import com.company.model.utils.*;
import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.MealItem;
import com.company.model.entity.User;
import com.company.model.service.IMealItemService;
import com.company.model.service.IPageService;
import com.company.model.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddNewProductCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddNewProductCommand.class);
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_CALORIES = "calories";
    private static final String PARAM_NAME_PROTEIN = "proteins";
    private static final String PARAM_NAME_FAT = "fats";
    private static final String PARAM_NAME_CARBS = "carbs";
    private IPageService pageService = ServiceFactory.getPageService();
    private IMealItemService productService = ServiceFactory.getMealItemService();

    /**
     * Adding user's food to list of food if such does not exist
     *
     * @return page path
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.addProduct");
        MealItem newProduct = getProductFromRequest(request);
        request.setAttribute("newProduct", newProduct);

        if (productService.checkIsMealItemExist(newProduct.getName())) {
            logger.info("Such product already exists!");
            request.setAttribute("errorProductExistMessage", MessageManager.getProperty("message.productexist"));
            return page;
        }

        if (productService.addMealItem(newProduct)) {
            session.setAttribute("successAddProductMessage", MessageManager.getProperty("message.addproductsuccess"));
            pageService.updateMainPageData(session, ((User) session.getAttribute("user")).getUserId());
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            session.setAttribute("errorAddProductMessage", MessageManager.getProperty("message.addproducterror"));
            page = ConfigurationManager.getProperty("path.page.addProduct");
        }
        return page;
    }

    /**
     * Getting MealItem from request
     *
     * @return MealItem product
     */
    private MealItem getProductFromRequest(HttpServletRequest request) {
        MealItem product = new MealItem(
                request.getParameter(PARAM_NAME_NAME),
                Integer.parseInt(request.getParameter(PARAM_NAME_FAT)),
                Integer.parseInt(request.getParameter(PARAM_NAME_CALORIES)),
                Integer.parseInt(request.getParameter(PARAM_NAME_PROTEIN)),
                Integer.parseInt(request.getParameter(PARAM_NAME_CARBS))
        );
        return product;
    }
}
