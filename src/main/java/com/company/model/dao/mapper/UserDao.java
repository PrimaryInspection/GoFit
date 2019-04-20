package com.company.model.dao.mapper;

import com.company.model.entity.User;

import java.util.List;

public interface UserDao extends CrudDao<User>{
    User get(String login);

    List<User> getAll(int limit, int offset);

    boolean updateStatus(User user);

    int getUsersCount();
}
