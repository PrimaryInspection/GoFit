package com.company.model.validation;

public interface IRegistationValidation {

    boolean isFirsNameUAValid(String firstName);
    boolean isFirsNameENValid(String firstName);

    boolean isSecondNameUAValid(String secondName);
    boolean isSecondNameENValid(String secondName);

    boolean isLoginValid(String login);

    boolean isPasswordValid(String password);
    boolean isPassConfValid(String confirmation);

    boolean isEmailValid(String email);

    boolean isWeightValid(int weight);

    boolean isGoalWeightvalid(int goalWeight);

    boolean isHeightValid(int height);
}
