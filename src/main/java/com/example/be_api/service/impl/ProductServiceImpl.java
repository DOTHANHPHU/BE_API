package com.example.be_api.service.impl;

import com.example.be_api.dto.ProductDto;
import com.example.be_api.entity.Product;
import com.example.be_api.controller.exception.ProductNotFoundException;
import com.example.be_api.mapper.ProductMapper;
import com.example.be_api.repository.ProductRepository;
import com.example.be_api.service.ImageService;
import com.example.be_api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//@Service
@Component
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;


    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map((product) -> ProductMapper.mapToProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + productId));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product createProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(createProduct);
    }

//    @Override
//    public ProductDto createProduct(ProductDto productDto, MultipartFile imageFile) {
//        // Xử lý tạo sản phẩm
//        Product product = ProductMapper.mapToProduct(productDto);
//        Product createProduct = productRepository.save(product);
//
//        // Xử lý tải lên ảnh và lưu tên tệp ảnh vào sản phẩm
//        String imageFileName = imageService.uploadImage(imageFile);
//        product.setImageFileName(imageFileName);
//        productRepository.save(product);
//
//        return ProductMapper.mapToProductDto(createProduct);
//    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto updateProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product is not exists with given id: " + productId)
                );

        product.setTitle(updateProduct.getTitle());
        product.setDescription(updateProduct.getDescription());

        Product updatedProductObj = productRepository.save(product);

        return ProductMapper.mapToProductDto(updatedProductObj);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product is not exists with given id: " + productId)
                );

        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDto> findByTitleContaining(String keyword) {
        List<Product> products = productRepository.findByTitleContaining(keyword);
        return products.stream().map((product) -> ProductMapper.mapToProductDto(product))
                .collect(Collectors.toList());
    }

}
