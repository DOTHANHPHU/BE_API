package com.example.be_api.mapper;

import com.example.be_api.dto.ProductDto;
import com.example.be_api.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
//        productDto.setImageFileName(product.getImageFileName());
        return productDto;
    }

    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
//        product.setImageFileName(productDto.getImageFileName());
        return product;
    }
}
