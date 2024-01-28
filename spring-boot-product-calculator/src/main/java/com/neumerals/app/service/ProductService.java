package com.neumerals.app.service;

import com.neumerals.app.dto.CalculateProductPriceDTO;
import com.neumerals.app.dto.ProductDTO;
import com.neumerals.app.entity.Product;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO product);
    ProductDTO updateProduct(ProductDTO product);
    CalculateProductPriceDTO calculatePriceByProductId(CalculateProductPriceDTO calculateDTO);
    ProductDTO getProductPriceByProductId(int productId);
    Product getProductByProductId(Integer productId);
}
