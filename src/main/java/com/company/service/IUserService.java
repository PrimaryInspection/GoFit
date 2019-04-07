package com.company.service;


import com.company.model.User;

import java.util.List;

public interface IUserService {
    User getUser(String login);

    User getUser(int id);

    List<User> getAll();

    boolean addUser(User user);

    boolean updateUser(User newUser);

    boolean blockUnblockUser(User user);

    List<User> getUsers(int limit, int offset);

    int getUsersCount();

}
