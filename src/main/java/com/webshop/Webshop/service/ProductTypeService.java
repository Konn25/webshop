package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.ProductType;
import com.webshop.Webshop.jpa.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeService implements ProductTypeServiceInterface{

    private final ProductTypeRepository productTypeRepository;

    @Override
    public Optional<ProductType> getProductTypeById(Long id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public void deleteProductTypeById(Long id) {
        Optional<ProductType> foundProductType = productTypeRepository.findById(id);

        if (foundProductType.isPresent()){
            productTypeRepository.deleteById(id);
        }

    }

    @Override
    public ProductType createNewProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    @Override
    public ProductType updateProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    @Override
    public List<ProductType> getAllProductType() {
        return productTypeRepository.findAll();
    }
}
