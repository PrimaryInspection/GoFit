package com.company.controller.command.impl.admin;

import com.company.model.service.IPageService;
import com.company.controller.command.impl.ActionCommand;
import com.company.model.service.factory.ServiceFactory;
import com.company.model.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToNPageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ToNPageCommand.class);

    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * Move to the next page in the list of users
     *
     * @return next page path
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.admin");

        pageService.updateAdminPageData(request);
        return page;
    }
}
