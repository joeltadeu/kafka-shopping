package com.sk.shopcart.service;

import com.sk.commons.dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.sk.commons.util.ShopTopics.SHOP_TOPIC_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendKafkaMessageService {

  private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

  public void sendMessage(ShopDTO msg) {
    log.info("Send message to kafka {}", msg);
    kafkaTemplate.send(SHOP_TOPIC_NAME, msg.getBuyerIdentifier(), msg);
  }
}
