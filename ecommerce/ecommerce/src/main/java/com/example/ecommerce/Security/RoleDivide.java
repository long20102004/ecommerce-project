package com.example.ecommerce.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
public class RoleDivide {
    @Bean
    public SecurityFilterChain manageRoles(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> {
            request.requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll();
        });
        httpSecurity.csrf(csrf -> {csrf.disable();});
        return httpSecurity.build();
    }
}
