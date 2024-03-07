package com.example.ecommerce.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@CrossOrigin
public interface EcommerceRepo extends JpaRepository<Product, Integer> {
    // must have spring data jpa rest dependency
}
