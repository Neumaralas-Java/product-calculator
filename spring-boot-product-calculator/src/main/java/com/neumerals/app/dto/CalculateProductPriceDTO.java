package com.neumerals.app.dto;

import com.neumerals.app.entity.QuantityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculateProductPriceDTO implements Serializable {

    private int productId;

    @NotNull(message = "Quantity can't be empty or null")
    private int quantity;

    @NotNull(message = "QuantityType can't be empty or null")
    private QuantityType quantityType;

    @Min(value = 0, message = "Total Price price can't be a negative value")
    private double totalPrice;

    public ProductPricesDTO getTotalPrice() {
        return new ProductPricesDTO(quantity,totalPrice);
    }
}
