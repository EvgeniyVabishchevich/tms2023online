package by.tms.eshopspringboot.model;

import by.tms.eshopspringboot.model.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(name = "login")
    @NotEmpty(message = "Login field is empty")
    private String login;

    @Column(name = "name")
    @NotEmpty(message = "Name field is empty")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Name first letter must be capitalized and contain at least 2 symbols")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Surname field is empty")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Surname first letter must be capitalized and contain at least 2 symbols")
    private String surname;

    @Column(name = "email")
    @NotEmpty(message = "Email field is empty")
    @Email(message = "Incorrect email address")
    private String email;

    @Column(name = "birthday")
    @Past(message = "This date must be in past")
    private LocalDate birthday;

    @Column(name = "password")
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
}

