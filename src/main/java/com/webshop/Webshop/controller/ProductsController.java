package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.PictureDTO;
import com.webshop.Webshop.dto.ProductsDTO;
import com.webshop.Webshop.jpa.Picture;
import com.webshop.Webshop.jpa.Products;
import com.webshop.Webshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Products> getProductsById(@PathVariable("id") Long id) {
        return productService.getProductsById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewProducts(@RequestBody ProductsDTO productsDTO) {

        Products productsRequest = modelMapper.map(productsDTO, Products.class);

        Products newProducts = productService.createNewProducts(productsRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("New product added to the database! ID: " + newProducts.getId());

    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductsDTO productsDTO) {

        Products productsRequest = modelMapper.map(productsDTO, Products.class);

        Products newProducts = productService.createNewProducts(productsRequest);

        return ResponseEntity.status(HttpStatus.OK)
                             .body("Product Updated ID: " + newProducts.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductsById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted!!");
    }

}
