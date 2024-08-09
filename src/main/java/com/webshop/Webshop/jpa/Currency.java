package com.webshop.Webshop.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;



}
