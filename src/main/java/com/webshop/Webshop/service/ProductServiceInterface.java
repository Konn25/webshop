package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.Products;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {

    Optional<Products> getProductsById(Long id);

    void deleteProductsById(Long id);

    Products createNewProducts(Products products);

    Products updateProducts(Products products);

    List<Products> getAllProducts();

}
