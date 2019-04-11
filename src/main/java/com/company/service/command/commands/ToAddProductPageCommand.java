package com.company.service.command.commands;

import com.company.model.User;
import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToAddProductPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.addProduct");
        pageService.updateUserData(request.getSession(), ((User) request.getSession().getAttribute("user")).getUserId());

        return page;
    }
}
