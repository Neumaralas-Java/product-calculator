package com.neumerals.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductPricesDTO {
    private int units;
    private double price;
}
