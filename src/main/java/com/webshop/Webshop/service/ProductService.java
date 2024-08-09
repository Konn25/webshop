package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.Orders;
import com.webshop.Webshop.jpa.Products;
import com.webshop.Webshop.jpa.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface{

    private final ProductsRepository productsRepository;

    @Override
    public Optional<Products> getProductsById(Long id) {
        return productsRepository.findById(id);
    }

    @Override
    public void deleteProductsById(Long id) {
        Optional<Products> foundOrders = productsRepository.findById(id);

        if (foundOrders.isPresent()){
            productsRepository.deleteById(id);
        }
    }

    @Override
    public Products createNewProducts(Products user) {
        return productsRepository.save(user);
    }

    @Override
    public Products updateProducts(Products user) {
        return productsRepository.save(user);
    }

    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }
}
