package BankApplication.data.services;

import BankApplication.services.interfaces.ImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Autowired
    private ImageService imageService;

    @Test
    void testThatCanUploadKycDocument() throws IOException {

        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                "passport.jpg",
                "jpg",
                "Passport".getBytes()
        );

        String url = imageService.upload(multipartFile);

        assertNotNull(url);




    }
}