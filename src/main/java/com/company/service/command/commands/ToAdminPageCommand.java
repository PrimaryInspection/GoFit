package com.company.service.command.commands;

import com.company.exceptions.PageNotFoundException;
import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToAdminPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * select all users from DB and forwards to admin page
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.admin");
        if(page == null){throw new PageNotFoundException(ConfigurationManager.getProperty("path.page.erro"));}
        pageService.updateAdminPageData(request);
        return page;
    }
}
