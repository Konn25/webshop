package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.ProductsDTO;
import com.webshop.Webshop.jpa.Products;
import com.webshop.Webshop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product API", description = "The Product API ")
public class ProductsController {

    private final ProductService productService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    @Operation(summary = "Get all product", description = "Return all product from the database")
    @ApiResponse(responseCode = "200", description = "Got all product")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id", description = "Return product by id from the database")
    @ApiResponse(responseCode = "200", description = "Product found by id")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public Optional<Products> getProductsById(@PathVariable("id") Long id) {
        return productService.getProductsById(id);
    }

    @PostMapping("/new")
    @Operation(summary = "Create a product", description = "Create a product")
    @ApiResponse(responseCode = "201", description = "Product created")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> createNewProducts(@RequestBody ProductsDTO productsDTO) {

        Products productsRequest = modelMapper.map(productsDTO, Products.class);

        Products newProducts = productService.createNewProducts(productsRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("New product added to the database! ID: " + newProducts.getId());

    }

    @PostMapping("/update")
    @Operation(summary = "Update a product", description = "Update a product by id")
    @ApiResponse(responseCode = "201", description = "Product created")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> updateProduct(@RequestBody ProductsDTO productsDTO) {

        Products productsRequest = modelMapper.map(productsDTO, Products.class);

        Products newProducts = productService.createNewProducts(productsRequest);

        return ResponseEntity.status(HttpStatus.OK)
                             .body("Product Updated ID: " + newProducts.getId());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Product delete", description = "Product deleted by id")
    @ApiResponse(responseCode = "200", description = "Product deleted")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductsById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted!!");
    }

}
