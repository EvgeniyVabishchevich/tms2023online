package by.tms.eshopspringboot.model;

import by.tms.eshopspringboot.model.enums.UserType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UserType userType;
    @NotEmpty(message = "Login field is empty")
    private String login;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthday;
    private int id;
    @NotEmpty(message = "Password field is empty")
    @Size(min = 5, max = 20, message = "Password must contain 5-20 symbols")
    private String password;

    public User(UserType userType, String login, String name, String surname, String email, LocalDate birthday) {
        this.userType = userType;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

