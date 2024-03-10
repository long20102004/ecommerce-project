package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class ProductService {
    ProductRepo productRepo;
    @Autowired
    public ProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }
    public ModelAndView getAllProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productRepo.findAll());
        modelAndView.setViewName("all-product");
        return modelAndView;
    }
    public ModelAndView getSpecificProduct(int productId){
        ModelAndView mav = new ModelAndView();
        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            mav.addObject("product", product.get());
            mav.addObject("products", productRepo.findAll());
            mav.setViewName("product-detail");
        }
        else mav.setViewName("redirect:/products");
        return mav;
    }
}
