package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyServiceInterface {

    Optional<Currency> getCurrencyById(Long id);

    void deleteCurrency(Long id);

    Currency createNewCurrency(Currency currency);

    Currency updateNewCurrency(Currency currency);

    List<Currency> getAllCurrency();

}
