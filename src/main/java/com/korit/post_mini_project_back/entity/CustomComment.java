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
public class CustomComment {
    private int commentId;
    private int postId;
    private String content;
    private LocalDateTime createdAt;
    private int level;
    private String path;

    private int parentCommentId;
    private int parentUserId;
    private String parentNickname;


    private int userid;
    private String nickname;
    private String imgUrl;
}
