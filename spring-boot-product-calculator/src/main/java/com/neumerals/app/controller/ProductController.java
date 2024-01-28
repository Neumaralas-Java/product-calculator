package com.neumerals.app.controller;

import com.neumerals.app.dto.CalculateProductPriceDTO;
import com.neumerals.app.dto.ProductPricesDTO;
import com.neumerals.app.entity.Product;
import com.neumerals.app.dto.ProductDTO;
import com.neumerals.app.entity.QuantityType;
import com.neumerals.app.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @PostMapping("/priceEngine")
    public ResponseEntity<?> calculatePriceByProductId(@RequestBody CalculateProductPriceDTO calculateDTO) throws Exception {

        try {
            Product product = productService.getProductByProductId(calculateDTO.getProductId());

            ProductPricesDTO totalPrice= calculateDTO.getTotalPrice();

            if (calculateDTO.getQuantityType() == QuantityType.CARTONS ){
                totalPrice = calculatePrice(product, calculateDTO.getQuantity()*(product.getQuantity()));
            } else if (calculateDTO.getQuantityType() == QuantityType.UNITS) {
                totalPrice = calculatePrice(product, calculateDTO.getQuantity());
            }

            return ResponseEntity.status(HttpStatus.OK).body(totalPrice);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/item/{productId}")
    public ResponseEntity<?> getProductByProductId(@PathVariable Integer productId) throws Exception {
        try {
            Product product = productService.getProductByProductId(productId);
            List<ProductPricesDTO> priceList = new ArrayList<>();

            for (int i = 1; i < 26; i++) {
                priceList.add(calculatePrice(product,i));
            }
            return ResponseEntity.status(HttpStatus.OK).body(priceList);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ProductPricesDTO calculatePrice(Product product, int units) throws Exception{
        double unitPrice=product.getCartPrice()/product.getQuantity();
        int numberOfCartons= units/product.getQuantity();
        int singleUnit= units % product.getQuantity();
        double totalPrice=(singleUnit*unitPrice)*(product.getDiscount()+100)/100;

        if (numberOfCartons>=2){
            totalPrice+=numberOfCartons*product.getCartPrice()*0.8;
        }else {
            totalPrice+=numberOfCartons*product.getCartPrice();
        }
        return new ProductPricesDTO(units,totalPrice);
    }
}
