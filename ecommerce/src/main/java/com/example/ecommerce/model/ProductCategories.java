package com.example.ecommerce.model;

import jakarta.persistence.*;
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
