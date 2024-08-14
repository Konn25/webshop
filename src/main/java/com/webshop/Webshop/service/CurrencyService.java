package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.Currency;
import com.webshop.Webshop.jpa.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService implements CurrencyServiceInterface {

    private final CurrencyRepository currencyRepository;

    /**
     *  Get a currency by id form the database
     * @param id
     * @return the right currency
     */
    @Override
    public Optional<Currency> getCurrencyById(Long id) {
        return currencyRepository.findCurrencyById(id);
    }

    /**
     * Delete a currency by id from the database
     * @param id
     */
    @Override
    public void deleteCurrency(Long id) {
        Optional<Currency> foundCurrency = currencyRepository.findCurrencyById(id);

        if (foundCurrency.isPresent()) {
            currencyRepository.deleteById(id);
        }

    }

    /**
     * Create a new currency to the database
     * @param currency
     * @return Save a currency to the database
     */
    @Override
    public Currency createNewCurrency(Currency currency) {
       return currencyRepository.save(currency);
    }

    /**
     * Update a currency
     * @param currency
     * @return Save an updated currency
     */

    @Override
    public Currency updateNewCurrency(Currency currency) {

        Optional<Currency> foundCurrency = currencyRepository.findCurrencyById(currency.getId());

        foundCurrency.ifPresent(value -> currency.setId(value.getId()));

        return currencyRepository.save(currency);
    }

    /**
     * Get all currency from the database
     * @return All currency
     */
    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }
}
