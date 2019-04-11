package com.company.service.impl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.LifestyleDao;
import com.company.dao.interfaces.UserDao;
import com.company.model.Lifestyle;
import com.company.model.User;
import com.company.service.IUserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UserService implements IUserService{
    private final static Logger logger = LogManager.getLogger(UserService.class);
    private static UserDao userDao = DaoFactory.getUserMethods();
    private static LifestyleDao lifestyleDao = DaoFactory.getLifestyleMethods();

    private static UserService instance = new UserService();

    private  UserService(){}

    public static UserService getInstance(){return instance;}

//TODO try to add other commands and after fix registration
    @Override
    public User getUser(String login) {
        logger.info("Getting user by login: " + login);
        return userDao.get(login);
    }

    @Override
    public User getUser(int id) {
        logger.info("Getting user by id: " + id);
        return userDao.getItem(id);
    }

    @Override
    public List<User> getAll() {
    return userDao.getAll();
    }

    @Override
    public boolean addUser(User user) {
        user.setCalories_norm(this.calculateCalorieNorm(user));
        return userDao.addItem(user);
    }

    @Override
    public List<Lifestyle> getLifestyles() {
        return lifestyleDao.getAll();
    }
    @Override
    public Lifestyle getLifestyle(Integer lifestyleId) { return lifestyleDao.getItem(lifestyleId); }


    @Override
    public boolean updateUser(User newUser) {
        newUser.setCalories_norm(calculateCalorieNorm(newUser));
        return userDao.updateItem(newUser);
    }

    @Override
    public boolean blockUnblockUser(User user) {
        return userDao.updateStatus(user);
    }

    @Override
    public List<User> getUsers(int limit, int offset) {
        return userDao.getAll(limit,offset);
    }

    @Override
    public int getUsersCount() {
        return userDao.getUsersCount();
    }



    private int calculateCalorieNorm(User user) {
        if (user == null) {
            return 0;
        }
        int caloriesNorm = 0;
        Lifestyle lifestyle = this.getLifestyle(user.getLifestyle_id());
        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();


        switch (lifestyle.getName()){
            case "active": {
                caloriesNorm = (int) ((caloriesNorm * 1.4625d) + (10 * Float.parseFloat(String.valueOf(user.getWeight())) +  (6.25d * user.getHeight()) - 5 * age + 5));
                break;
            }
            case "average": {
                caloriesNorm = (int) ((caloriesNorm * 1.375d) + (10 * Float.parseFloat(String.valueOf(user.getWeight())) +  (6.25d * user.getHeight()) - 5 * age + 5));
                break;
            }
            case "lazy": {
                caloriesNorm = (int) ((caloriesNorm * 1.2d) + + (10 * Float.parseFloat(String.valueOf(user.getWeight())) +  (6.25d * user.getHeight()) - 5 * age + 5));
                break;
            }
            default: {
                logger.error("No lifestyle found.");
            }
        }
        return caloriesNorm;
    }
}
