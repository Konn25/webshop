package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.CurrencyDTO;
import com.webshop.Webshop.jpa.Currency;
import com.webshop.Webshop.service.CurrencyService;
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
public class CurrencyController {


    private final CurrencyService currencyService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<Currency> getAllCurrency(){
        return currencyService.getAllCurrency();
    }

    @GetMapping("/{id}")
    public Optional<Currency> getCurrencyById(@PathVariable("id") Long id){
        return currencyService.getCurrencyById(id);
    }



    @PostMapping("/new")
    public ResponseEntity<String> createNewCurrency(@RequestBody CurrencyDTO currencyDTO){

        Currency currencyRequest = modelMapper.map(currencyDTO, Currency.class);

        Currency newCurrency = currencyService.createNewCurrency(currencyRequest);

        CurrencyDTO currencyResponse = modelMapper.map(newCurrency, CurrencyDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("New currency added to the database! This currency: "+currencyResponse.getName());

    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCurrency(@RequestBody CurrencyDTO currencyDTO){

        Currency currencyRequest = modelMapper.map(currencyDTO, Currency.class);

        Currency newCurrency = currencyService.updateNewCurrency(currencyRequest);

        CurrencyDTO currencyResponse = modelMapper.map(newCurrency, CurrencyDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body("Currency updated!! To this: "+currencyResponse.getName());

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCurrencyById(@PathVariable("id") Long id){
        currencyService.deleteCurrency(id);

        return ResponseEntity.status(HttpStatus.OK).body("Currency deleted!");
    }


}
