package com.sk.shopcart.service;

import dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendKafkaMessageService {

  private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

  private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

  public void sendMessage(ShopDTO msg) {
    log.info("Send message to kafka {}", msg);
    kafkaTemplate.send(SHOP_TOPIC_NAME, msg.getBuyerIdentifier(), msg);
  }
}
