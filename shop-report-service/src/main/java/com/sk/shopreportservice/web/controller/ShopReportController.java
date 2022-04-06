package com.sk.shopreportservice.web.controller;

import com.sk.shopreportservice.persistence.repository.ShopReportRepository;
import com.sk.shopreportservice.web.dto.ShopReportDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
@Slf4j
@Tag(
    name = "Shop Report Api",
    description =
        "API responsible to show the order report.")
public class ShopReportController {

  private final ShopReportRepository reportRepository;

  @GetMapping
  @Operation(summary = "Returns the order report")
  public ResponseEntity<List<ShopReportDTO>> getShop() {
    log.info("order report...");
    return ResponseEntity.ok(reportRepository.findAll().stream()
        .map(ShopReportDTO::convert)
        .collect(Collectors.toList()));
  }
}
