package com.sk.shopcatalogservice.service;

import com.sk.shopcatalogservice.persistence.entity.Product;
import com.sk.shopcatalogservice.persistence.repository.ProductRepository;
import com.sk.commons.dto.ShopDTO;
import com.sk.commons.dto.ShopItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.sk.commons.util.ShopTopics.SHOP_TOPIC_EVENT_NAME;
import static com.sk.commons.util.ShopTopics.SHOP_TOPIC_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessageService {

  private final ProductRepository productRepository;

  private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

  @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
  public void listenShopTopic(ShopDTO shopDTO) {
    log.info("Order received on topic: {}", shopDTO.getIdentifier());

    boolean success = true;
    for (ShopItemDTO item : shopDTO.getItems()) {
      Product product = productRepository.findByIdentifier(item.getProductIdentifier());
      if (!isValidShop(item, product)) {
        shopError(shopDTO);
        success = false;
        break;
      }
    }
    if (success) {
      shopSuccess(shopDTO);
    }
  }

  private boolean isValidShop(ShopItemDTO item, Product product) {
    return product != null && product.getAmount() >= item.getAmount();
  }

  private void shopError(ShopDTO shopDTO) {
    log.info("Order processing error {}.", shopDTO.getIdentifier());
    shopDTO.setStatus("ERROR");
    kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
  }

  private void shopSuccess(ShopDTO shopDTO) {
    log.info("Order {} successfully completed.", shopDTO.getIdentifier());
    shopDTO.setStatus("SUCCESS");
    kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
  }
}
