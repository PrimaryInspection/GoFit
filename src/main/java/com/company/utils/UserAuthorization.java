package com.company.utils;
import com.company.model.Role;
import java.util.Objects;

public class UserAuthorization {

    private String login;
    private Role roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthorization that = (UserAuthorization) o;
        return Objects.equals(login, that.login) &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, roleId);
    }

    public void setEmail(String login) {
        this.login = login;
    }

    public void setRole(Role roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return login;
    }

    public Role getRole() {
        return roleId;
    }
}
