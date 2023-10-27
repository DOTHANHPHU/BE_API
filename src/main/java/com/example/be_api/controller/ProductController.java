package com.example.be_api.controller;

import com.example.be_api.dto.EmployeeDto;
import com.example.be_api.dto.ProductDto;
import com.example.be_api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Build Get All Products REST API
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Build Get By Id Products REST API
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long productId) {
        ProductDto productDto = productService.getProductById(productId);
        return ResponseEntity.ok(productDto);
    }

//    // Build Add Product REST API
//    @PostMapping
//    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto,
//                                           @RequestParam("imageFile") MultipartFile imageFile) {
//        ProductDto createdProduct = productService.createProduct(productDto, imageFile);
//        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//    }

    // Build Add Product REST API
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Build Update Product REST API
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long productId,
                                           @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(productId, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    // Build Delete Product REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully!.");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProductsByKeyword(@RequestParam(value = "keyword", required = false, defaultValue = "Mi") String keyword) {
        List<ProductDto> products = productService.findByTitleContaining(keyword);

        if (products.isEmpty()) {
            // Nếu không tìm thấy sản phẩm nào, có thể trả về mã HTTP 404 (Not Found)
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }

        return ResponseEntity.ok(products);
    }
}
