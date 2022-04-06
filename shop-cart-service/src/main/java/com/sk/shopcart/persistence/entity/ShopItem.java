package com.sk.shopcart.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "shop_item")
public class ShopItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_identifier")
  private String productIdentifier;

  private Integer amount;
  private Float price;

  @ManyToOne
  @JoinColumn(name = "shop_id")
  private Shop shop;


}
