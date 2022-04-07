package com.sk.shopcart.service;

import com.sk.shopcart.persistence.entity.Shop;
import com.sk.shopcart.persistence.repository.ShopRepository;
import com.sk.commons.dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.sk.commons.util.ShopTopics.SHOP_TOPIC_EVENT_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessageService {
  private final ShopRepository shopRepository;

  @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
  public void listenShopEvents(ShopDTO shopDTO) {
    try {
      log.info("Order's status received on topic: {}", shopDTO.getIdentifier());

      Shop shop = shopRepository.findByIdentifier(shopDTO.getIdentifier());
      shop.setStatus(shopDTO.getStatus());
      shopRepository.save(shop);
    } catch (Exception e) {
      log.error("Order processing error {}", shopDTO.getIdentifier());
    }
  }
}
