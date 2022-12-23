package com.cybersoft.food_project.service;

import com.cybersoft.food_project.model.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private Path rootPath;

    @Autowired
    public FileUploadServiceImpl(FileStorageProperties fileStorageProperties) throws IOException {
        //Định nghĩa đường dẫn root
        this.rootPath = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        if (Files.notExists(this.rootPath)) {
            // Tạo folder lưu file  nếu không tồn tại folder
            Files.createDirectories(rootPath);
        }
    }
    @Override
    public boolean storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            System.out.println("Loi save file" + e.getMessage());
            return false;
        }
    }

    @Override
    public Resource loadFileByName(String fileName) {

        //byte[]
        //base64
        //resourse

        try {
            Path path = this.rootPath.resolve(fileName).normalize();
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            }
        } catch (Exception e) {
            System.out.println("Loi doc file" + e.getMessage());
        }
        return null;

    }
}
