package com.company.service.command.commands;

import com.company.model.User;
import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
