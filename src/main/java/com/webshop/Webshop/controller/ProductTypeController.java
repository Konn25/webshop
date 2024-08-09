package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.ProductTypeDTO;
import com.webshop.Webshop.jpa.ProductType;
import com.webshop.Webshop.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/product/type")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<ProductType> getAllProductType() {
        return productTypeService.getAllProductType();
    }

    @GetMapping("/{id}")
    public Optional<ProductType> getProductTypeById(@PathVariable("id") Long id) {
        return productTypeService.getProductTypeById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewProductType(@RequestBody ProductTypeDTO productTypeDTO) {

        ProductType productTypeRequest = modelMapper.map(productTypeDTO, ProductType.class);

        ProductType newProductType = productTypeService.createNewProductType(productTypeRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("New product type added to the database! ID: " + newProductType.getId());

    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProductType(@RequestBody ProductTypeDTO productTypeDTO) {

        ProductType productTypeRequest = modelMapper.map(productTypeDTO, ProductType.class);

        ProductType newProductType = productTypeService.updateProductType(productTypeRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Product type Updated! ID: " + newProductType.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductType(@PathVariable("id") Long id) {
        productTypeService.deleteProductTypeById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product type deleted!!");
    }

}
