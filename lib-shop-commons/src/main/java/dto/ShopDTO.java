package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShopDTO {
  @Schema(description = "Order identifier", name = "identifier", example = "c098e6a1-505e-4c7b-8888-b02641be9336")
  private String identifier;

  @Schema(description = "Order date", name = "dateShop", example = "2022-04-08")
  private LocalDate dateShop;

  @Schema(description = "Order status", name = "status", example = "[PENDING, ERROR, SUCCESS]")
  private String status;

  @NotNull(message = "Buyer identifier cannot be null")
  @Schema(description = "Buyer identifier", name = "buyerIdentifier", example = "e42ecf81-08a3-496b-a202-143e2988fadf")
  private String buyerIdentifier;

  @NotEmpty(message = "order items list cannot be empty.")
  @Schema(description = "Order items", name = "items")
  private List<ShopItemDTO> items = new ArrayList<>();
}
