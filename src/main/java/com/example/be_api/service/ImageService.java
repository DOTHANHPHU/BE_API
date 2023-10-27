package com.example.be_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    String uploadImage(MultipartFile file);
}
