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

    @Column(insertable=false, updatable=false)
    Long orderStatusId;

    String date;

    @Column(insertable=false, updatable=false)
    Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderItemId")
    OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "orderStatusId")
    OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "userId")
    Users user;


}
