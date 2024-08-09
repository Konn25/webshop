package com.webshop.Webshop.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String username;

    String password;

    String email;

    boolean admin;

    String country;

    String city;

    String address;

    int mobile;
}
