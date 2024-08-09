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

    Long productTypeId;

    String name;

    double price;

    @Column(insertable=false, updatable=false)
    Long currencyId;

    Long pictureId;

    int quantity;

    @ManyToOne
    @JoinColumn(name = "currencyId")
    Currency currency;

    @ManyToOne
    @JoinColumn(name = "productId")
    OrderItem orderItemProduct;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "products", cascade = CascadeType.ALL)
    List<ProductType> productType;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "products", cascade = CascadeType.ALL)
    List<Picture> picture;




}
