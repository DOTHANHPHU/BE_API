package com.example.be_api.controller;

import com.example.be_api.dto.ProductDto;
import com.example.be_api.controller.exception.ProductNotFoundException;
import com.example.be_api.service.ImageService;
import com.example.be_api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/products/images")
public class ImageUploadController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;

    @PostMapping("/{id}")
    public ResponseEntity<String> uploadImage(@PathVariable("id") Long productId, @RequestParam("file") MultipartFile file) {
        ProductDto product = productService.getProductById(productId);

        // Kiểm tra xem sản phẩm có tồn tại không
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }

        // Thực hiện việc lưu trữ ảnh
        String imageFileName = imageService.uploadImage(file);

        // Cập nhật tên file ảnh vào sản phẩm
        product.setImageFileName(imageFileName);
        productService.updateProduct(productId, product);

        return ResponseEntity.ok("Image uploaded successfully.");
    }
}
