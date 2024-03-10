package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.exceptions.UserExistedException;
import com.example.ecommerce.model.CustomUserDetail;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

@Service
public class UserService {
    UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
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
        if (userRepo.findByUsername(userDto.getUsername()) != null) throw new UserExistedException("User existed");
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
