package com.sk.shopcart.web.controller;

import com.sk.shopcart.persistence.entity.Shop;
import com.sk.shopcart.persistence.entity.ShopItem;
import com.sk.shopcart.persistence.repository.ShopRepository;
import com.sk.shopcart.service.SendKafkaMessageService;
import com.sk.shopcart.web.mapper.ShopMapper;
import dto.ShopDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
@Slf4j
@Tag(
    name = "Shop Cart Api",
    description =
        "API responsible for listing and saving orders.")
public class ShopController {
  private final ShopRepository shopRepository;
  private final SendKafkaMessageService sendKafkaMessage;
  private final ShopMapper mapper;

  @GetMapping
  @Operation(summary = "Returns the list of orders")
  public ResponseEntity<List<ShopDTO>> getShop() {
    log.info("listing orders...");
    return ResponseEntity.ok(shopRepository.findAll().stream().map(mapper::convert).collect(Collectors.toList()));
  }

  @PostMapping
  @Operation(summary = "Send order to kafka for processing")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Order in progress",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ShopDTO.class))
              }),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid account number supplied",
              content = @Content)

      })
  public ResponseEntity<ShopDTO> saveShop(@RequestBody @Valid ShopDTO shopDTO) {
    log.info("create an order...");
    shopDTO.setIdentifier(UUID.randomUUID().toString());
    shopDTO.setDateShop(LocalDate.now());
    shopDTO.setStatus("PENDING");

    Shop shop = mapper.convert(shopDTO);
    for (ShopItem shopItem : shop.getItems()) {
      shopItem.setShop(shop);
    }

    shopDTO = mapper.convert(shopRepository.save(shop));
    log.info("order to be processing {}", shopDTO);
    sendKafkaMessage.sendMessage(shopDTO);
    return ResponseEntity.ok(shopDTO);
  }
}
