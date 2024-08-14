package com.webshop.Webshop.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(insertable=false, updatable=false)
    Long productTypeId;

    String name;

    double price;

    @Column(insertable=false, updatable=false)
    Long currencyId;

    @Column(insertable=false, updatable=false)
    Long pictureId;

    int quantity;

    @ManyToOne
    @JoinColumn(name = "currencyId")
    Currency currency;


    @ManyToOne
    @JoinColumn(name = "productTypeId")
    ProductType productType;

    @ManyToOne
    @JoinColumn(name = "pictureId")
    Picture picture;

}
