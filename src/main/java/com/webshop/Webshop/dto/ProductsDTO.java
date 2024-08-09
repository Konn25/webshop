package com.webshop.Webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {

    Long id;

    Long productTypeId;

    String name;

    double price;

    Long currencyId;

    Long pictureId;

    int quantity;


}
