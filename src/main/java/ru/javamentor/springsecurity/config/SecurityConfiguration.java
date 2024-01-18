package ru.javamentor.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/admin", "/new", "/dekete").hasRole("ADMIN")
                .requestMatchers("/","/login", "/registration").permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin((form) -> form
                .loginPage("/login")
                .successHandler(userAuthenticationSuccessHandler())
        );
        http.logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler userAuthenticationSuccessHandler(){
        return new SuccessUserHandler();
    }

    @Bean
    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}
