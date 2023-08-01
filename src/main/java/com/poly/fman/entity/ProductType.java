package com.poly.fman.entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * The persistent class for the product_type database table.
 */
@Entity
@Table(name = "product_type")
@NamedQuery(name = "ProductType.findAll", query = "SELECT p FROM ProductType p")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private byte active;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "productType")
    private List<Product> products;


    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product addProduct(Product product) {
        getProducts().add(product);
        product.setProductType(this);

        return product;
    }

    public Product removeProduct(Product product) {
        getProducts().remove(product);
        product.setProductType(null);

        return product;
    }

}