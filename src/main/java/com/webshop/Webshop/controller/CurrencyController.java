package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.CurrencyDTO;
import com.webshop.Webshop.jpa.Currency;
import com.webshop.Webshop.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/currency")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerToken")
@Tag(name = "Currency API", description = "The Currency API ")
public class CurrencyController {


    private final CurrencyService currencyService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    @Operation(summary = "Get all currency", description = "Return all currency from the database")
    @ApiResponse(responseCode = "200", description = "Get all currency")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public List<Currency> getAllCurrency(){
        return currencyService.getAllCurrency();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get currency by id", description = "Get currency by id from the database")
    @ApiResponse(responseCode = "200", description = "Get currency")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public Optional<Currency> getCurrencyById(@PathVariable("id") Long id){
        return currencyService.getCurrencyById(id);
    }



    @PostMapping("/new")
    @Operation(summary = "Add new currency", description = "Add new currency to the database")
    @ApiResponse(responseCode = "201", description = "New currency added to the database")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> createNewCurrency(@RequestBody CurrencyDTO currencyDTO){

        Currency currencyRequest = modelMapper.map(currencyDTO, Currency.class);

        Currency newCurrency = currencyService.createNewCurrency(currencyRequest);

        CurrencyDTO currencyResponse = modelMapper.map(newCurrency, CurrencyDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("New currency added to the database! This currency: "+currencyResponse.getName());

    }

    @PostMapping("/update")
    @Operation(summary = "Update currency", description = "Update currency ")
    @ApiResponse(responseCode = "200", description = "Currency updated")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> updateCurrency(@RequestBody CurrencyDTO currencyDTO){

        Currency currencyRequest = modelMapper.map(currencyDTO, Currency.class);

        Currency newCurrency = currencyService.updateNewCurrency(currencyRequest);

        CurrencyDTO currencyResponse = modelMapper.map(newCurrency, CurrencyDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body("Currency updated!! To this: "+currencyResponse.getName());

    }



    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete currency", description = "Delete currency by id from the database")
    @ApiResponse(responseCode = "200", description = "Currency deleted")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> deleteCurrencyById(@PathVariable("id") Long id){
        currencyService.deleteCurrency(id);

        return ResponseEntity.status(HttpStatus.OK).body("Currency deleted!");
    }


}
