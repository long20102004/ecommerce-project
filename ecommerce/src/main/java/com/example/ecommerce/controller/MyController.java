package com.example.ecommerce.controller;

import com.example.ecommerce.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MyController {
    private EcommerceRepo ecommerceRepo;
    private UserRepo userRepo;
    @Autowired
    public MyController(EcommerceRepo ecommerceRepo,
                        UserRepo userRepo){
        this.ecommerceRepo = ecommerceRepo;
        this.userRepo = userRepo;
    }
    @GetMapping("")
    public String landingPage(){
        return "landing-page";
    }
    @GetMapping("/products")
    public String getAllProduct(Model model){
        model.addAttribute("products", ecommerceRepo.findAll());
        return "all-product";
    }
    @GetMapping("/product/{productId}")
    public String getSpecificProduct(@PathVariable("productId")int productId, Model model){
        Optional<Product> product = ecommerceRepo.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("products", ecommerceRepo.findAll());
            return "product-detail";
        }
        else return "redirect:/products";
    }
    @GetMapping("/login")
    public String login(){
        return "sign-in";
    }
    @GetMapping("/sign-up")
    public String signUp(){
        return "sign-up";
    }
    @PostMapping("/sign-up")
    public String submitSignUpForm(@RequestParam("username")String username,
                                   @RequestParam("password")String password,
                                   @RequestParam("name")String name){
        User user = new User(username, name);
        Account account = new Account(username, password);
        user.setAccount(account);
        userRepo.save(user);
        return "redirect:/";
    }

}
