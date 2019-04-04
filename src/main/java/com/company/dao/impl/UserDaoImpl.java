package com.company.dao.impl;

import com.company.dao.interfaces.UserDao;
import com.company.model.User;

import java.util.List;

public class UserDaoImpl extends CrudDaoImpl<User> implements UserDao {

    @Override
    public User get(String login) {
        return null;
    }

    @Override
    public List<User> getAll(int limit, int offset) {
        return null;
    }

    @Override
    public int getUsersCount() {
        return 1;
    }
}
