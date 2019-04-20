package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.User;
import com.company.model.service.IPageService;
import com.company.model.service.factory.ServiceFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.company.model.utils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ToMainPageCommand implements ActionCommand {
    private final static Logger logger = LogManager.getLogger(ToMainPageCommand.class);
    private IPageService pageService = ServiceFactory.getPageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.main");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null){
            page=ConfigurationManager.getProperty("path.page.login");
        }else{
            pageService.updateMainPageData(session,user.getUserId());
        }
        return page;
    }
}
