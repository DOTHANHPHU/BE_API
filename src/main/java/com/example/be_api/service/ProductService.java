package com.example.be_api.service;

import com.example.be_api.dto.EmployeeDto;
import com.example.be_api.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(Long productId);
    public ProductDto createProduct(ProductDto productDto);

//    ProductDto createProduct(ProductDto productDto, MultipartFile imageFile);

    public ProductDto updateProduct(Long productId, ProductDto productDto);
    public void deleteProduct(Long productId);
    public List<ProductDto> findByTitleContaining(String keyword);
}
