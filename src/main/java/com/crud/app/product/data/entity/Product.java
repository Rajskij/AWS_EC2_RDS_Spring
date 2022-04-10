package com.crud.app.product.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "PRODUCT_URL")
    private String productUrl;
    @Column(name = "PRODUCT_PRICE")
    private String productPrice;
}
