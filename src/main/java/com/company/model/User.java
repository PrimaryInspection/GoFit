package com.company.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class User implements Serializable {
    private Integer userId;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private String email;
    private LocalDate birthday;
    private Integer weight;
    private Integer weightGoal;
    private Integer height;
    private Integer calories_norm;
    private Integer lifestyle_id;
    private Integer status;
    private Integer roleId;



    public User() {
    }


    /**
     * Constructor to input user to DB
     * */
    public User(String firstName, String secondName, String login, String password, String email,
                LocalDate birthday, Integer weight, Integer weightGoal, Integer height,
                Integer lifestyle_id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.weight = weight;
        this.weightGoal = weightGoal;
        this.height = height;
        this.lifestyle_id = lifestyle_id;

    }

    /**
     * Constructor to output user from DB
     * */
    public User(Integer userId, String firstName, String secondName, String login, String password,
                String email, LocalDate birthday, Integer weight, Integer weightGoal, Integer height,
                Integer calories_norm, Integer lifestyle_id , Integer status , Integer roleId) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.weight = weight;
        this.weightGoal = weightGoal;
        this.height = height;
        this.calories_norm = calories_norm;
        this.lifestyle_id = lifestyle_id;
        this.status=status;
        this.roleId = roleId;

    }

    public Integer getRoleId() { return roleId; }

    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSecondName() { return secondName; }

    public void setSecondName(String secondName) { this.secondName = secondName; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public LocalDate getBirthday() { return birthday; }

    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public Integer getWeight() { return weight; }

    public void setWeight(Integer weight) { this.weight = weight; }

    public Integer getWeightGoal() { return weightGoal; }

    public void setWeightGoal(Integer weightGoal) { this.weightGoal = weightGoal; }

    public Integer getHeight() { return height; }

    public void setHeight(Integer height) { this.height = height; }

    public Integer getCalories_norm() { return calories_norm; }

    public void setCalories_norm(Integer calories_norm) { this.calories_norm = calories_norm; }

    public Integer getLifestyle_id() { return lifestyle_id; }

    public void setLifestyle_id(Integer lifestyle_id) { this.lifestyle_id = lifestyle_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getSecondName(), user.getSecondName()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getBirthday(), user.getBirthday()) &&
                Objects.equals(getWeight(), user.getWeight()) &&
                Objects.equals(getWeightGoal(), user.getWeightGoal()) &&
                Objects.equals(getHeight(), user.getHeight()) &&
                Objects.equals(getCalories_norm(), user.getCalories_norm()) &&
                Objects.equals(getLifestyle_id(), user.getLifestyle_id()) &&
                Objects.equals(getStatus() , user.getStatus());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserId(), getFirstName(), getSecondName(), getLogin(), getPassword(), getEmail(), getBirthday(), getWeight(), getWeightGoal(), getHeight(), getCalories_norm(), getLifestyle_id(),getStatus(),getRoleId());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", weightGoal=" + weightGoal +
                ", height=" + height +
                ", calories_norm=" + calories_norm +
                ", lifestyle='" + lifestyle_id + '\'' +
                ", status='" + status + '\'' +
                ", status='" + roleId + '\'' +
                '}';
    }
}
