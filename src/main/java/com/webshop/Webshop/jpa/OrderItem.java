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
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long productId;

    Long orderId;

    int orderItemQuantity;

    double orderItemPrice;

    @Column(insertable=false, updatable=false)
    Long currencyId;

    @ManyToOne
    @JoinColumn(name = "currencyId")
    Currency currency;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderItemProduct", cascade = CascadeType.ALL)
    List<Products> products;

    @ManyToOne
    @JoinColumn(name = "orderItemsId")
    Orders orders;

}
