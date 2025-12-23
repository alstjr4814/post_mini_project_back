package com.korit.post_mini_project_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageFile {
    private int imageFileId;
    private String category;
    private String originalFilename;
    private String filePath;
    private String extension;
    private long size;
    private LocalDateTime createdAt;
    private int referenceId;
}