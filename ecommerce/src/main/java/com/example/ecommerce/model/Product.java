package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sku")
    private String sku;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_price")
    private BigDecimal price;
    @Column(name = "image_url")
    private String imgUrl;
    @Column(name = "active")
    private boolean active;
    @Column(name = "units_in_stock")
    private int unitInStock;
    @Column(name = "date_created")
    private Date dateCreated;
    @Column(name = "last_updated")
    private Date lastUpdated;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategories productCategories;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_user",
            joinColumns = @JoinColumn(name = "fk_product", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "fk_user", referencedColumnName = "username"))
    private List<User> users;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", active=" + active +
                ", unitInStock=" + unitInStock +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", productCategories=" + productCategories +
                '}';
    }
}
