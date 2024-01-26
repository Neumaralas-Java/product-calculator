package com.neumerals.app.controller;

import com.neumerals.app.entity.Quantity;
import com.neumerals.app.dto.ProductDTO;
import com.neumerals.app.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveProduct(@RequestBody @Valid ProductDTO productDTO) throws Exception {
        return productService.saveProduct(productDTO);
    }

    @PutMapping(path = "/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) throws Exception {
        return productService.updateProduct(productDTO);

    }

    @GetMapping("/{productId}")
    public ProductDTO getProductPriceByProductId(@PathVariable int productId) throws Exception {
        ProductDTO productDTO = productService.getProductPriceByProductId(productId);

        return productDTO;
    }

    @GetMapping("/priceEngine")
    public List<Double> calculateProductPrice(@RequestParam String productName, @RequestParam Quantity quantity)
            throws Exception {
        return productService.calculateProductPrice(productName, quantity);
    }
}
