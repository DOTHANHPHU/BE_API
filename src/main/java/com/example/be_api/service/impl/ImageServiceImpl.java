package com.example.be_api.service.impl;

import com.example.be_api.controller.exception.ImageUploadException;
import com.example.be_api.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@Service
@Component
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    // Đường dẫn thư mục lưu trữ ảnh
    private static final String IMAGE_UPLOAD_DIR = "/E:/BE_API/src/main/resources/images/";

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            // Tạo thư mục nếu chưa tồn tại
            File uploadDir = new File(IMAGE_UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Lưu tệp ảnh vào thư mục đã xác định
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(IMAGE_UPLOAD_DIR, fileName);
            Files.write(filePath, file.getBytes());

            return fileName;
        } catch (IOException e) {
            throw new ImageUploadException("Failed to upload image.");
        }
    }
}
