package by.tms.eshopspringboot.security;

import by.tms.eshopspringboot.enums.Role;
import by.tms.eshopspringboot.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static by.tms.eshopspringboot.utils.Constants.MappingPath.ADMIN;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.LOGIN;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String H2_CONSOLE_PATH = "/h2-console/**";

    private final UserDetailsService userDetailsService;
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/" + ADMIN).hasRole(Role.ADMIN.name())
                        .requestMatchers("/" + USER).hasRole(Role.USER.name())
                        .anyRequest()
                        .permitAll())
                .formLogin(form -> form
                        .loginPage("/" + LOGIN)
                        .permitAll()
                        .usernameParameter(LOGIN_PARAMETER)
                        .passwordParameter(PASSWORD_PARAMETER)
                        .successForwardUrl("/" + Constants.MappingPath.LOGIN_SUCCESS))
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/" + LOGIN)
                        .permitAll())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler))
                .headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }
}