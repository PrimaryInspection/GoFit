package com.company.service;

public interface ILoginRegistrationService {

    boolean checkLogin(String login,String pass);

    boolean confirmPassword(String password, String passwordConfirm);

    boolean checkLoginExist(String login);
}
