package com.example.ecommerce.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityDetail {
    @Bean
    public SecurityFilterChain manageRoles(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(form -> {
            form.loginPage("/login").permitAll();
        });
        httpSecurity.authorizeHttpRequests(request -> {
            request.requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/my-cart").hasRole("USER")
                    .requestMatchers("/add-product").hasRole("USER")
                    .anyRequest().permitAll();
        });
        httpSecurity.csrf(csrf -> {csrf.disable();});
        return httpSecurity.build();
    }
}
