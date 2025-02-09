package org.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

    @Value("photos.dir")
    private String uploadDir;

    public String load(MultipartFile file) {
        try {
            // Перевіряємо, чи файл не порожній і чи існує директорія
            if (file.isEmpty()) return "";
            Files.createDirectories(Paths.get(uploadDir));

            // Отримуємо ім'я файлу та створюємо шлях для збереження
            var fileName = file.getOriginalFilename();
            var fileExt = fileName != null && fileName.contains(".")
                    ? fileName.substring(fileName.lastIndexOf("."))
                    : "";
            var newFileName = UUID.randomUUID() + fileExt;
            Path filePath = Paths.get(uploadDir, newFileName);

            // Зберігаємо файл на сервер
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public void remove(String fileName) {
        try {
            Path filePath = Paths.get(fileName);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String replace(String oldFileName, MultipartFile newFile) {
        var newFileName = load(newFile);
        if (newFileName == ""){
            return oldFileName;
        }
        remove(oldFileName);
        return newFileName;
    }
}