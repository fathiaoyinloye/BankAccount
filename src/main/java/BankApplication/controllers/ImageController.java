package BankApplication.controllers;

import BankApplication.data.services.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    @Autowired
    private ImageServiceImpl imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload (@RequestParam("file")MultipartFile multipartFile){
        try {
            return ResponseEntity.ok(imageService.upload(multipartFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
