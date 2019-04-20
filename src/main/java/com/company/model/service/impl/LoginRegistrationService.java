package com.company.model.service.impl;

import com.company.model.dao.mapper.DaoFactory;
import com.company.model.dao.mapper.UserDao;
import com.company.model.entity.User;
import com.company.model.service.ILoginRegistrationService;
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
        logger.info(" This is checkLogin service ,Geting user from DB with login = " + login);
        User user = userDao.get(login);
        if(user == null){
            logger.info("This is checkLogin service, user was not found");
            return false;
        }
        logger.info("We was got user by login : " + login);
        logger.info("Checking user password...");
        if(user.getPassword().equals(password)){
            logger.info("Password is ok");
        }
        return user.getPassword().equals(password);

    }

    @Override
    public boolean confirmPassword(String password, String passwordConfirm) {
        logger.info("Checking password confirmation...");

        if(password.equals(passwordConfirm)){
            logger.info("Confirm password OK");
            return  password.equals(passwordConfirm);
        }else
            logger.info("Confirm password NOT OK!!");
        return false;
    }

    @Override
    public boolean checkLoginExist(String login) {
        logger.info("check if login exists");
         if(userDao.get(login) != null){
             logger.info("Such login does't existing");
         }
         return userDao.get(login) != null;
    }
}
