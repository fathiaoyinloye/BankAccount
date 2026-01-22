package BankApplication.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String upload (MultipartFile multipartFile) throws IOException;
    String viewImage (String fileName);
}
