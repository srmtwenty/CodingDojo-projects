package io.github.jyotinaruka.friendbook.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    
   public static void saveFile(Path uploadPath, String fileName,
           MultipartFile multipartFile) throws IOException {
        
       if (!Files.exists(uploadPath)) {
           Files.createDirectories(uploadPath);
       }
        
       try (InputStream inputStream = multipartFile.getInputStream()) {
           Path filePath = uploadPath.resolve(fileName);
           System.out.println("Saving to " + filePath);
           Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
       } catch (IOException ioe) {        
           throw new IOException("Could not save image file: " + fileName, ioe);
       }      
   }
}