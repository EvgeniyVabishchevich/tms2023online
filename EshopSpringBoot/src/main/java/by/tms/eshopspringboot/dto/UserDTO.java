package by.tms.eshopspringboot.dto;

import by.tms.eshopspringboot.enums.Role;
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
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO {
    private Long id;
    private Set<Role> roles;
    @NotEmpty(message = "Login field is empty")
    private String login;

    @NotEmpty(message = "Name field is empty")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Name first letter must be capitalized and contain at least 2 symbols")
    private String name;

    @NotEmpty(message = "Surname field is empty")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Surname first letter must be capitalized and contain at least 2 symbols")
    private String surname;

    @NotEmpty(message = "Email field is empty")
    @Email(message = "Incorrect email address")
    private String email;

    @Past(message = "This date must be in past")
    private LocalDate birthday;

    @NotEmpty(message = "Password field is empty")
    @Size(min = 4, max = 20, message = "Password must contain 5-20 symbols")
    private String password;

    public UserDTO(Set<Role> roles, String login, String name, String surname, String email, LocalDate birthday) {
        this.roles = roles;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
    }
}
