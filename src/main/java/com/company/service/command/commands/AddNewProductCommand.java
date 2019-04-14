package com.company.service.command.commands;

import com.company.model.MealItem;
import com.company.model.User;
import com.company.service.IMealItemService;
import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import com.company.utils.MessageManager;
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

    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
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
