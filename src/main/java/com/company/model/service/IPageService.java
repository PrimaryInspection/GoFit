package com.company.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IPageService {
    boolean isRedirect();

    void setRedirect(boolean redirect);

    void updateMainPageData(HttpSession session, int userId);

    void updateRegistrationPageData(HttpServletRequest request);

    void updateAdminPageData(HttpServletRequest request);

    void updateUserData(HttpSession session, Integer userId);
}
