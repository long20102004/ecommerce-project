package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes("product")
public class MyController {
    private ProductService productService;
    private UserService userService;
    @Autowired

    public MyController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("")
    public ModelAndView landingPage(Model model){
        return userService.showLandingPage();
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
    public String login(Model model){
        model.addAttribute("user", new User());
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
    @GetMapping("not-found")
    public String notFound(){
        return "not-found";
    }
    @GetMapping("/my-cart")
    public ModelAndView showCart(){
        return userService.showCart();
    }
    @PostMapping("/add-product")
    public ModelAndView addProduct(@ModelAttribute("product")Product product){
        return userService.addProduct(product);
    }
}
