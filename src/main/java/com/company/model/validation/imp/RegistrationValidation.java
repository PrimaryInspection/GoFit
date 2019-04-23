package com.company.model.validation.imp;

import com.company.model.utils.RegexManager;
import com.company.model.validation.IRegistationValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidation implements IRegistationValidation{

    private static RegistrationValidation instance = new RegistrationValidation();

    public RegistrationValidation() {
    }

    public static RegistrationValidation getInstance() {
        return instance;
    }


    @Override
    public boolean isFirsNameUAValid(String firstName) {
        if (firstName == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("uname.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(firstName);
        return m.matches();
    }

    @Override
    public boolean isFirsNameENValid(String firstName) {
        if (firstName == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("uname.en"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(firstName);
        return m.matches();
    }

    @Override
    public boolean isSecondNameUAValid(String secondName) {
        if (secondName == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("uname.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(secondName);
        return m.matches();
    }

    @Override
    public boolean isSecondNameENValid(String secondName) {
        if (secondName == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("uname.en"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(secondName);
        return m.matches();
    }

    @Override
    public boolean isLoginValid(String login) {
        if (login == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("ulogin"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(login);
        return m.matches();
    }

    @Override
    public boolean isPasswordValid(String password) {
        if (password == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("upassword"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    @Override
    public boolean isPassConfValid(String confirmation) {
        if (confirmation == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("upassword"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(confirmation);
        return m.matches();
    }

    @Override
    public boolean isEmailValid(String email) {
        if (email == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("uemail"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public boolean isWeightValid(int weight) {
        return weight > 0;

    }

    @Override
    public boolean isGoalWeightvalid(int goalWeight) {
        return goalWeight > 0;
    }

    @Override
    public boolean isHeightValid(int height) {
        return height > 0;
    }

}
