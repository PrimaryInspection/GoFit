package com.company.dao.interfaces;

import com.company.model.User;

import java.util.List;

public interface UserDao extends CrudDao<User>{
    User get(String login);

    List<User> getAll(int limit, int offset);

    boolean updateStatus(User user);

    int getUsersCount();
}
