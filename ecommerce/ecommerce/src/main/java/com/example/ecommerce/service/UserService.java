package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.model.CustomUserDetail;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

@Repository
public class UserService {
    @Autowired
    UserRepo userRepo;

    public void registerNewUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getUsername(), userDto.getPassword());
        userRepo.save(user);
    }

    public ModelAndView signUpForm() {
        UserDto userDto = new UserDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userDto);
        modelAndView.setViewName("sign-up");
        return modelAndView;
    }

    public ModelAndView postUserData(UserDto userDto, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("redirect:/sign-up");
        } else {
            registerNewUser(userDto);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
}
