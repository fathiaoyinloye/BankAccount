package BankApplication.services.implementations;

import BankApplication.services.interfaces.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;
        Path url = Paths.get("uploads/kyc/");
        if (!Files.exists(url)){
            Files.createDirectories(url);
        }
        Files.copy(multipartFile.getInputStream(), url.resolve(fileName));
        return fileName;
    }
    @Override
    public String viewImage(String fileName) {
        return "";
    }

}
