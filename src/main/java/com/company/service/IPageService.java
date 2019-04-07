package com.company.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IPageService {

    boolean isRedirect();

    void updateMainPageData(HttpSession session, int userId);

    void setRedirect(boolean isRedirected);

    void updateRegistrationPageData(HttpServletRequest request);

    void updateAdminPageData(HttpServletRequest request);

    void updateUser(HttpSession session, Integer userId);
}
