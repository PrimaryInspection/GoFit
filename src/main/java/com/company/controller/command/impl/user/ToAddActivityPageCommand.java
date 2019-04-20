package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.User;
import com.company.model.service.IPageService;
import com.company.model.service.factory.ServiceFactory;

import com.company.model.utils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToAddActivityPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.addActivity");
        pageService.updateUserData(request.getSession(), ((User) request.getSession().getAttribute("user")).getUserId());

        return page;
    }
}
