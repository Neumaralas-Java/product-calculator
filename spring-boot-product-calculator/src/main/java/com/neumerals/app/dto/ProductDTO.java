package com.neumerals.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO implements Serializable {

    private int id;

    @NotBlank(message = "product can't be empty or null")
    private String productName;

    @NotNull(message = "Quantity can't be empty or null")
    private int quantity;

    @NotNull(message = "Cart price can't be empty or null")
    @Min(value = 0, message = "Cart price can't be a negative value")
    private double cartPrice;

    @Min(value = 0, message = "Discount can't be a negative value")
    private double discount;
}
