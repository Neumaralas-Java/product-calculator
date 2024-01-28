package com.neumerals.app.service.impl;

import com.neumerals.app.dto.CalculateProductPriceDTO;
import com.neumerals.app.dto.ProductDTO;
import com.neumerals.app.entity.Product;
import com.neumerals.app.repository.ProductRepository;
import com.neumerals.app.service.ProductService;
import com.neumerals.app.service.exception.BOException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        ProductDTO saveProduct = new ProductDTO();
        if (!productRepository.existsById(product.getId())){
            Product savedProduct = productRepository.save(product);
            saveProduct = modelMapper.map(savedProduct,ProductDTO.class);
        }else {
            throw new BOException("product id already Exists");
        }
        return saveProduct;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        ProductDTO updatedProduct = new ProductDTO();
        if (productRepository.existsById(productDTO.getId())){
            product.setProductName(productDTO.getProductName());
            product.setQuantity(product.getQuantity());
            product.setCartPrice(product.getCartPrice());
            product.setDiscount(product.getDiscount());
            updatedProduct = modelMapper.map(productRepository.save(product),ProductDTO.class);
        }else {
            throw new BOException("No product found for that id");
        }
        return updatedProduct;

    }

    @Override
    public CalculateProductPriceDTO calculatePriceByProductId(CalculateProductPriceDTO calculateDTO) {
        Product product=productRepository.findById(calculateDTO.getProductId()).orElseThrow(()->new BOException("No product found for that "+calculateDTO.getProductId()));
        CalculateProductPriceDTO calculatorDTO = modelMapper.map(product, CalculateProductPriceDTO.class);

        return calculatorDTO;

    }

    @Override
    public ProductDTO getProductPriceByProductId(int productId) {
        Product product =productRepository.getById(productId);
        ProductDTO productDTO =modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    @Override
    public Product getProductByProductId(Integer productId) {
        return productRepository.findById(productId).orElseThrow(()->new BOException("No product found for that"+productId));
    }
}