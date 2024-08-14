package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.ProductTypeDTO;
import com.webshop.Webshop.jpa.ProductType;
import com.webshop.Webshop.service.ProductTypeService;
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
@RequestMapping("/v1/product/type")
@RequiredArgsConstructor
@Tag(name = "Product Type API", description = "The Product Type API ")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    @Operation(summary = "Get all product type", description = "Return all product type from the database")
    @ApiResponse(responseCode = "200", description = "Get product type")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public List<ProductType> getAllProductType() {
        return productTypeService.getAllProductType();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product type by id", description = "Return product type by id from the database")
    @ApiResponse(responseCode = "200", description = "Get product type by id")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public Optional<ProductType> getProductTypeById(@PathVariable("id") Long id) {
        return productTypeService.getProductTypeById(id);
    }

    @PostMapping("/new")
    @Operation(summary = "Create new product type", description = "Create new product type")
    @ApiResponse(responseCode = "201", description = "Create new product type")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> createNewProductType(@RequestBody ProductTypeDTO productTypeDTO) {

        ProductType productTypeRequest = modelMapper.map(productTypeDTO, ProductType.class);

        ProductType newProductType = productTypeService.createNewProductType(productTypeRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("New product type added to the database! ID: " + newProductType.getId());

    }

    @PostMapping("/update")
    @Operation(summary = "Update product type", description = "Update product type")
    @ApiResponse(responseCode = "200", description = "Update product type")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> updateProductType(@RequestBody ProductTypeDTO productTypeDTO) {

        ProductType productTypeRequest = modelMapper.map(productTypeDTO, ProductType.class);

        ProductType newProductType = productTypeService.updateProductType(productTypeRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Product type Updated! ID: " + newProductType.getId());
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete product type", description = "Delete product type by id")
    @ApiResponse(responseCode = "200", description = "Delete product type")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> deleteProductType(@PathVariable("id") Long id) {
        productTypeService.deleteProductTypeById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product type deleted!!");
    }

}
