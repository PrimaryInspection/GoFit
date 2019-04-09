package com.company.service.impl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.ActivityDao;
import com.company.dao.interfaces.UserDao;
import com.company.model.User;
import com.company.service.ILoginRegistrationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoginRegistrationService implements ILoginRegistrationService{
    private final static Logger logger = LogManager.getLogger(LoginRegistrationService.class);
    private static UserDao userDao = DaoFactory.getUserMethods();
    private static LoginRegistrationService instance = new LoginRegistrationService();

    public LoginRegistrationService(){}
    public static LoginRegistrationService getInstance(){return instance;}


    @Override
    public boolean checkLogin(String login, String password) {
        logger.info("Geting user from DB with login = " + login);
        User user = userDao.get(login);
        logger.debug("We was got user  : " + user.toString());
        if(user == null){
            logger.info("user was not found");
            return false;
        }
        logger.info("Checking user password...");
        return  user.getPassword().equals(password);
    }

    @Override
    public boolean confirmPassword(String password, String passwordConfirm) {
        logger.info("Checking password confirmation...");
        return password.equals(passwordConfirm);
    }

    @Override
    public boolean checkLoginExist(String login) {
        logger.info("check if login exists");
        return userDao.get(login) != null;
    }
}
