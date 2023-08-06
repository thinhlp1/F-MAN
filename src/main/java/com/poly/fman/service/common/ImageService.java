package com.poly.fman.service.common;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;

@RestController
public class ImageService {
    @GetMapping ("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException{
        // Tạo đối tượng Resource từ đường dẫn tương đối
        Resource resource = new ClassPathResource("static/admin-resource/plugins/images/" + imageName);

        // Kiểm tra xem file có tồn tại không
        if (resource.exists()) {
            // Trả về file hình ảnh
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE)
                    .body(resource);
        } else {
            String errorMessage = "Image not found: " + imageName;
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ByteArrayResource(errorMessage.getBytes()));
        }
    }
}
