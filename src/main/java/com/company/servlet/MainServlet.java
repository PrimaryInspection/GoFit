package com.company.servlet;


import com.company.dao.factory.DaoFactory;
import com.company.dao.impl.ActivityItemDaoImpl;
import com.company.dao.impl.MealItemDaoImpl;
import com.company.dao.interfaces.ActivityDao;
import com.company.dao.interfaces.ActivityItemDao;
import com.company.dao.interfaces.MealItemDao;
import com.company.dao.interfaces.UserDao;
import com.company.model.ActivityItem;
import com.company.model.MealItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);
    private String path = "/WEB-INF/view/index.jsp";
    private static UserDao userDao = DaoFactory.getUserMethods();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("items",userDao.getAll()); // It now available as ${items} in EL.
        request.getRequestDispatcher(path).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }




}
