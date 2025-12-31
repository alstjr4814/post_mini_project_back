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
public class Comment {
    private int commentId;
    private int postId;
    private int parentCommentId;
    private int parentUserId;
    private int userId;
    private String content;
    private LocalDateTime createdAt;
}
