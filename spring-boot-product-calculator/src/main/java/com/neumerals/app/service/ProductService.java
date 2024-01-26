package com.neumerals.app.service;

import com.neumerals.app.dto.ProductDTO;
import com.neumerals.app.entity.Quantity;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO product);
    ProductDTO updateProduct(ProductDTO product);
    List<Double> calculateProductPrice(String productName, Quantity quantity);
    ProductDTO getProductPriceByProductId(int productId);
}
