package com.company.service.serviceImpl;

import com.company.service.IPageService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PageService implements IPageService {
    private static final Logger logger= LogManager.getLogger(PageService.class);
    private static PageService instance = new PageService();

    private static final String PARAM_NAME_LOGIN = "pageNumber";
    private boolean redirect = false;

    private PageService() {}

    public static PageService getInstance() {
        return instance;
    }


    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public void updateMainPageData(HttpSession session, int userId) {

    }

    @Override
    public void setRedirect(boolean isRedirected) {

    }

    @Override
    public void updateRegistrationPageData(HttpServletRequest request) {

    }

    @Override
    public void updateAdminPageData(HttpServletRequest request) {

    }

    @Override
    public void updateUser(HttpSession session, Integer userId) {

    }
}
