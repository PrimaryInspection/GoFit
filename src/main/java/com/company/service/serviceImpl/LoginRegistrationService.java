package com.company.service.serviceImpl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.UserDao;
import com.company.model.User;
import com.company.service.ILoginRegistrationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoginRegistrationService implements ILoginRegistrationService {
    private static final Logger logger= LogManager.getLogger(LoginRegistrationService.class);
    private static UserDao userDao= DaoFactory.getUserMethods();
    private static LoginRegistrationService instance=LoginRegistrationService.getInstance();

    private LoginRegistrationService() {}

    public static LoginRegistrationService getInstance() {return instance;}

    @Override
    public boolean checkLogin(String login, String pass) {
        logger.info("Get user with login = " + login + ", and password = " + pass);
        User user=userDao.get(login);
        logger.debug("------------ User: " + user);
        if(user == null){
            logger.info("User with login = '" + login + "' was not found");
            return false;
        }
        logger.info("Check user's password");
        return user.getPassword().equals(pass);

    }

    @Override
    public boolean confirmPassword(String password, String passwordConfirm) {
        logger.info("Check confirmation of user's password");
        return password.equals(passwordConfirm);
    }

    @Override
    public boolean checkLoginExist(String login) {
        logger.info("check if login already exists");
        return userDao.get(login) !=null;
    }
}
