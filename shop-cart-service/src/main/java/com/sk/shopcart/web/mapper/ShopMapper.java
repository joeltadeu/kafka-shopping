package com.sk.shopcart.web.mapper;

import com.sk.shopcart.persistence.entity.Shop;
import com.sk.shopcart.persistence.entity.ShopItem;
import dto.ShopDTO;
import dto.ShopItemDTO;

import java.util.stream.Collectors;

public class ShopMapper {
    public ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setIdentifier(shop.getIdentifier());
        shopDTO.setDateShop(shop.getDateShop());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setBuyerIdentifier(shop.getBuyerIdentifier());
        shopDTO.setItems(
            shop.getItems().stream().map(this::convert).collect(Collectors.toList()));
        return shopDTO;
    }

    public ShopItemDTO convert(ShopItem shopItem) {
        ShopItemDTO shopItemDTO = new ShopItemDTO();
        shopItemDTO.setProductIdentifier(shopItem.getProductIdentifier());
        shopItemDTO.setAmount(shopItem.getAmount());
        shopItemDTO.setPrice(shopItem.getPrice());
        return shopItemDTO;
    }

    public ShopItem convert(ShopItemDTO shopItemDTO) {
        ShopItem shopItem = new ShopItem();
        shopItem.setProductIdentifier(shopItemDTO.getProductIdentifier());
        shopItem.setAmount(shopItemDTO.getAmount());
        shopItem.setPrice(shopItemDTO.getPrice());
        return shopItem;
    }

    public Shop convert(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setIdentifier(shopDTO.getIdentifier());
        shop.setStatus(shopDTO.getStatus());
        shop.setDateShop(shopDTO.getDateShop());
        shop.setItems(
            shopDTO.getItems().stream().map(this::convert).collect(Collectors.toList()));
        shop.setBuyerIdentifier(shopDTO.getBuyerIdentifier());
        return shop;
    }
}
