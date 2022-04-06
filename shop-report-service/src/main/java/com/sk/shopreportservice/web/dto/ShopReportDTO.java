package com.sk.shopreportservice.web.dto;

import com.sk.shopreportservice.persistence.entity.ShopReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopReportDTO {

  private String identifier;

  private Integer amount;

  public static ShopReportDTO convert(ShopReport shopReport) {
    ShopReportDTO shopDTO = new ShopReportDTO();
    shopDTO.setIdentifier(shopReport.getIdentifier());
    shopDTO.setAmount(shopReport.getAmount());
    return shopDTO;
  }
}
