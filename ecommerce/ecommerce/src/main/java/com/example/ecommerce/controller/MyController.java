package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    private ProductService productService;
    private UserService userService;
    @Autowired

    public MyController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("")
    public String landingPage(){
        return "landing-page";
    }
    @GetMapping("/products")
    public ModelAndView getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/product/{productId}")
    public ModelAndView getSpecificProduct(@PathVariable("productId")int productId){
        return productService.getSpecificProduct(productId);
    }
    @GetMapping("/login")
    public String login(){
        return "sign-in";
    }
    @GetMapping("/sign-up")
    public ModelAndView signUp(){
        return userService.signUpForm();
    }
    @PostMapping("/sign-up")
    public ModelAndView submitSignUpForm(@ModelAttribute("user") @Valid UserDto userDto, Errors errors){
        return userService.postUserData(userDto, errors);
    }

}
