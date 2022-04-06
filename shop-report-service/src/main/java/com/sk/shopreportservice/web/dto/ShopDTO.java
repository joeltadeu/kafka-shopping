package com.sk.shopreportservice.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {
  @Schema(description = "Order identifier", name = "identifier", example = "c098e6a1-505e-4c7b-8888-b02641be9336")
  private String identifier;

  @Schema(description = "Order status", name = "status", example = "[PENDING, ERROR, SUCCESS]")
  private String status;
}
