package com.company.model.service.impl;

import com.company.model.dao.mapper.DaoFactory;
import com.company.model.dao.mapper.LifestyleDao;
import com.company.model.dao.mapper.UserDao;
import com.company.model.entity.Lifestyle;
import com.company.model.entity.User;
import com.company.model.service.IUserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UserService implements IUserService{
    private final Float HEIGHT_FACTOR =new Float(6.25f);
    private final Integer COMMON_FACTOR =new Integer(5);
    private final Integer WEIGHT_FACTOR =new Integer(10);

    private final static Logger logger = LogManager.getLogger(UserService.class);
    private static UserDao userDao = DaoFactory.getUserMethods();
    private static LifestyleDao lifestyleDao = DaoFactory.getLifestyleMethods();
    private static UserService instance = new UserService();

    private  UserService(){}

    /**
     * Getting singletone instance of UserService
     * @return UserService
     */
    public static UserService getInstance(){return instance;}

    /**
     * Getting user from database by login
     * @return User user
     */
    @Override
    public User getUser(String login) {
        logger.info("Getting user by login: " + login);
        return userDao.get(login);
    }
    /**
     * Getting user from database by id
     * @return User user
     */
    @Override
    public User getUser(int id) {
      logger.info("Getting user by id: " + id);
        return userDao.getItem(id);
    }
    /**
     * Getting all users from database
     * @return List<User>
     */
    @Override
    public List<User> getAll() {
    return userDao.getAll();
    }

    /**
     * Setting calories norm, using user's body stats , then register new user and add to database
     * @return boolean ( true if addition is done)
     */
    @Override
    public boolean addUser(User user) {
        user.setCalories_norm(this.calculateCalorieNorm(user));
        return userDao.addItem(user);
    }
    /**
     * Getting all lifestyles from database
     * @return List<Lifestyle>
     */
    @Override
    public List<Lifestyle> getLifestyles() {
        return lifestyleDao.getAll();
    }

    /**
     * Getting lifestyle item from database
     * @return Lifestyle
     */
    @Override
    public Lifestyle getLifestyle(Integer lifestyleId) { return lifestyleDao.getItem(lifestyleId); }


    /**
     * Updating calories norm, using user's body stats
     * @return boolean ( true if updating is done)
     */
    @Override
    public boolean updateUser(User newUser) {
        newUser.setCalories_norm(calculateCalorieNorm(newUser));
        return userDao.updateItem(newUser);
    }

    /**
     * change user status
     * @return boolean ( true if updating status is done)
     */
    @Override
    public boolean blockUnblockUser(User user) {
        return userDao.updateStatus(user);
    }

    /**
     * Getting list of users for admin's page.
     * @param limit - count of users in 1 page
     * @return  List<User>
     */
    @Override
    public List<User> getUsers(int limit, int offset) {
        return userDao.getAll(limit,offset);
    }

    /**
     * Getting count of registered users
     * @return  int
     */
    @Override
    public int getUsersCount() {
        return userDao.getUsersCount();
    }


    /**
     * Calculating user's calories norm for each lifestyle, using special factors
     * @return  int
     */
    private int calculateCalorieNorm(User user) {
        if (user == null) {
            return 0;
        }
        int caloriesNorm = 0;
        Lifestyle lifestyle = this.getLifestyle(user.getLifestyle_id());
        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();


        switch (lifestyle.getName()){
            case "active": {
                caloriesNorm = (int) ((caloriesNorm * 1.4625f) + (WEIGHT_FACTOR * user.getWeight() +  (HEIGHT_FACTOR * user.getHeight()) - (COMMON_FACTOR * age) + COMMON_FACTOR));
                break;
            }
            case "average": {
                caloriesNorm = (int) ((caloriesNorm * 1.375f) + (WEIGHT_FACTOR * user.getWeight() +  (HEIGHT_FACTOR * user.getHeight()) - (COMMON_FACTOR * age) + COMMON_FACTOR));
                break;
            }
            case "lazy": {
                caloriesNorm = (int) ((caloriesNorm * 1.2f)  + (WEIGHT_FACTOR * user.getWeight() +  (HEIGHT_FACTOR * user.getHeight()) - (COMMON_FACTOR * age) + COMMON_FACTOR));
                break;
            }
            default: {
                logger.error("No lifestyle found.");
            }
        }
        return caloriesNorm;
    }
}
