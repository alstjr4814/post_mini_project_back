package com.korit.post_mini_project_back.service;


import com.korit.post_mini_project_back.entity.ImageFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FileService {

    @Value("${user.dir}")
    private String projectPath;

    public List<ImageFile> upload(String category, List<MultipartFile> files) {

        List<ImageFile> imageFiles = new ArrayList<>();

        if (files == null || files.isEmpty()) {
            return null;
        }

        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename();
            String newFilename = UUID.randomUUID() + "_" + originalFileName;
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".")  + 1); // 확장자 .jpg .txt 이런거
            Path uploadDirPath = Paths.get(projectPath, "upload", category);
            try {
                // 경로가 존재하지 않으면 자동으로 전체 폴더 경로 생성
                Files.createDirectories(uploadDirPath);
            } catch (IOException e) {}
            Path filePath = uploadDirPath.resolve(newFilename);
            try {
                file.transferTo(filePath);
            } catch (IOException e) {}
            imageFiles.add(ImageFile.builder()
                    .category(category)
                    .originalFilename(originalFileName)
                    .filePath(filePath.toString())
                    .extension(extension)
                    .size(file.getSize())
                    .build());
        }

        return imageFiles;
    }

}