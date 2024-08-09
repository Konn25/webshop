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

    @Override
    public Optional<Currency> getCurrencyById(Long id) {
        return currencyRepository.findCurrencyById(id);
    }

    @Override
    public void deleteCurrency(Long id) {
        Optional<Currency> foundCurrency = currencyRepository.findCurrencyById(id);

        if (foundCurrency.isPresent()) {
            currencyRepository.deleteById(id);
        }

    }

    @Override
    public Currency createNewCurrency(Currency currency) {
       return currencyRepository.save(currency);
    }

    @Override
    public Currency updateNewCurrency(Currency currency) {

        Optional<Currency> foundCurrency = currencyRepository.findCurrencyById(currency.getId());

        foundCurrency.ifPresent(value -> currency.setId(value.getId()));

        return currencyRepository.save(currency);
    }

    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }
}
