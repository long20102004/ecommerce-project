package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@NoArgsConstructor
public class ProductCategories {
    @Column(name = "id")
    @Id
    private int id;
    @Column(name = "categoryName")
    private String categoryName;
    @OneToMany(mappedBy = "productCategories")
    private List<Product> productList;
}
