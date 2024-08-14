package com.webshop.Webshop.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(insertable=false, updatable=false)
    Long productId;

    @Column(insertable=false, updatable=false)
    Long orderId;

    int orderItemQuantity;

    double orderItemPrice;

    @Column(insertable=false, updatable=false)
    Long currencyId;

    @ManyToOne
    @JoinColumn(name = "currencyId")
    Currency currency;



    @ManyToOne
    @JoinColumn(name = "orderId")
    Orders orders;

    @ManyToOne
    @JoinColumn(name = "productId")
    Products products;

}
