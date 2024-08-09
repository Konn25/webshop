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
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(insertable=false, updatable=false)
    Long userId;

    Long orderStatusId;

    String date;

    Long orderItemId;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orders", cascade = CascadeType.ALL)
    List<OrderItem> orderItem;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orders", cascade = CascadeType.ALL)
    List<OrderStatus> orderStatus;

    @ManyToOne
    @JoinColumn(name = "userId")
    Users user;


}
