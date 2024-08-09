package com.webshop.Webshop.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findCurrencyById(Long id);

    Optional<Currency> findCurrencyByIdAndName(Long id, String name);

}
