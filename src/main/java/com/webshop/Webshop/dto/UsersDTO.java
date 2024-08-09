package com.webshop.Webshop.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    Long id;

    String username;

    String password;

    String email;

    boolean admin;

    String country;

    String city;

    String address;

    int mobile;

}
