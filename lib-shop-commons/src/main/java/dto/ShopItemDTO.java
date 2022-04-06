package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemDTO {
  @Schema(description = "Product identifier", name = "identifier", example = "c098e6a1-505e-4c7b-8888-b02641be9336")
  private String productIdentifier;

  @Schema(description = "Product amount", name = "amount", example = "15")
  private Integer amount;

  @Schema(description = "Product price", name = "price", example = "45.50")
  private Float price;
}
