package com.webshop.Webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

    Long id;

    Long userId;

    Long orderStatusId;

    String date;

    Long orderItemId;

}
