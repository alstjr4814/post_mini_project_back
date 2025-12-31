package com.korit.post_mini_project_back.dto.request;

import lombok.Data;

@Data
public class CreatePostCommentReqDto {
    private int parentCommentId;
    private int parentUserId;
    private String content;
}
