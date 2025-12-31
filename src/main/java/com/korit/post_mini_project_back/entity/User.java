package com.korit.post_mini_project_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String oauth2Id;
    private String nickname;
    private String name;
    private String email;
    private String provider;
    private String role;
    private String imgUrl;
    private String imgFilePath;
    private LocalDateTime createdAt;
}
