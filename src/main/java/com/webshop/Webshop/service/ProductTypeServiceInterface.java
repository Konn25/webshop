package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductTypeServiceInterface {

    Optional<ProductType> getProductTypeById(Long id);

    void deleteProductTypeById(Long id);

    ProductType createNewProductType(ProductType productType);

    ProductType updateProductType(ProductType productType);

    List<ProductType> getAllProductType();

}
