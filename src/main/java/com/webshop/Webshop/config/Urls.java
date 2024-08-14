package com.webshop.Webshop.config;

import lombok.Getter;

@Getter
public enum Urls {

    ADMIN("/v1/users/**", "/v1/picture/**", "/v1/products/**", "/v1/product/type/**"),
    USER("/v1/orders/**", "/v1/order/item/**", "/v1/order/status/**", "/v1/picture/all", "/v1/picture/{id}",
         "/v1/products/**", "/v1/product/type/**" ,"/v1/users/**"
    ),

    NOT_LOGGED_IN("/v1/auth/authenticate","/v1/auth/register", "/swagger-ui/**", "/v3/api-docs/**","/v2/api-docs/**",
                  "/swagger-resources/**", "/v1/currency/**"

                  );

    private final String[] urls;

    Urls(String... urls){
        this.urls = urls;
    }


}
