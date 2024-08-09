package com.webshop.Webshop.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String password;

    String email;

    boolean admin;

    String country;

    String city;

    String address;

    int mobile;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
    List<Orders> orders;

}
