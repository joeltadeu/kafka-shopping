package com.sk.shopreportservice.service;

import com.sk.shopreportservice.persistence.repository.ShopReportRepository;
import com.sk.shopreportservice.web.dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessageService {

  private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

  private final ShopReportRepository reportRepository;

  @Transactional
  @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group_report")
  public void listenShopTopic(ShopDTO shopDTO) {
    try {
      log.info("Order received on topic: {}.", shopDTO.getIdentifier());
      reportRepository.incrementShopStatus(shopDTO.getStatus());
    } catch (Exception e) {
      log.error("Message processing error", e);
    }
  }
}
