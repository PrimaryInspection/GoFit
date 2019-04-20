package com.company.model.service;

public interface ILoginRegistrationService {
    boolean checkLogin(String login, String password);

    boolean confirmPassword(String password , String passwordConfirm);

    boolean checkLoginExist(String login);
}
