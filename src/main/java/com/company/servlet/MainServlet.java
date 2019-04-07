package com.company.servlet;

import com.company.dao.factory.DaoFactory;
import com.company.dao.impl.MealItemDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.interfaces.ActivityDao;
import com.company.dao.interfaces.ActivityItemDao;
import com.company.dao.interfaces.MealItemDao;
import com.company.dao.interfaces.UserDao;
import com.company.model.ActivityItem;
import com.company.model.MealItem;
import com.company.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
