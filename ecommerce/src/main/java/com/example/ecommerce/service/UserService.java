package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.exceptions.UserExistedException;
import com.example.ecommerce.model.CustomUserDetail;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ProductCategories;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    public ModelAndView showLandingPage() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean status = (authentication != null) && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
        modelAndView.addObject("isLoggedIn", status);
        modelAndView.setViewName("landing-page");
        return modelAndView;
    }
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        if (!(authentication instanceof AnonymousAuthenticationToken)) return user;
        return null;
    }
    public ModelAndView showCart(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", getCurrentUser());
        modelAndView.setViewName("cart");
        return modelAndView;
    }
    public ModelAndView addProduct(Product product){
        User user = getCurrentUser();
        user.addProduct(product);
        System.out.println(product);
        userRepo.save(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/products");
        return modelAndView;
    }
}
