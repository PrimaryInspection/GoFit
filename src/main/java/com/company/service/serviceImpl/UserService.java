package com.company.service.serviceImpl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.UserDao;
import com.company.model.User;
import com.company.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UserService implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private static final UserDao userMethods = DaoFactory.getUserMethods();
    private static UserDao userDAO = userMethods;
    private static UserService instance = new UserService();

    private UserService() {}

    public static UserService getInstance() {
        return instance;
    }


    @Override
    public User getUser(String login) {
        return userDAO.get(login);
    }

    @Override
    public User getUser(int id) {
    return userDAO.getItem(id);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public boolean addUser(User user) {
        user.setCalories_norm(this.calculateCalorieNorm(user));
        return userDAO.addItem(user);
    }

    @Override
    public boolean updateUser(User newUser) {
        newUser.setCalories_norm(calculateCalorieNorm(newUser));
        return userDAO.updateItem(newUser);
    }

    @Override
    public boolean blockUnblockUser(User user) {
        return userDAO.updateStatus(user);
    }

    @Override
    public List<User> getUsers(int limit, int offset) {
        return userDAO.getAll(limit,offset);
    }

    @Override
    public int getUsersCount() {
        return userDAO.getUsersCount();
    }

    private int calculateCalorieNorm(User user) {
        if (user == null) {
            return 0;
        }
        int caloriesNorm = 0;
        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();

        switch (user.getLifestyle()){
            case "active": {
                caloriesNorm = (int) ((10 * user.getWeight() + (int) (6.25d * user.getHeight()) - 5 * age + 5)
                        + (caloriesNorm * 1.4625d));
                break;
            }
            case "average": {
                caloriesNorm = (int) ((10 * user.getWeight() + (int) (6.25d * user.getHeight()) - 5 * age + 5)
                        + (caloriesNorm * 1.375d));
                break;
            }
            case "lazy": {
                caloriesNorm = (int) ((10 * user.getWeight() + (int) (6.25d * user.getHeight()) - 5 * age + 5)
                + (caloriesNorm * 1.2d));
                break;
            }
            default: {
                logger.error("No lifestyle found.");
            }
        }
        return caloriesNorm;

    }
}
