package com.webshop.Webshop.config;

import lombok.Getter;

@Getter
public enum Urls {

    ADMIN("/v1/users/**"),
    USER("/v1/orders/**"),

    NOT_LOGGED_IN("/v1/auth/authenticate","/v1/auth/register");

    private final String[] urls;

    Urls(String... urls){
        this.urls = urls;
    }


}
