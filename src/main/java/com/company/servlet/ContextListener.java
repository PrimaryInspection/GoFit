package com.company.servlet;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.UserDao;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    UserDao userDao = DaoFactory.getUserMethods();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userDao.getAll();
        final ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("users",userDao.getAll());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
