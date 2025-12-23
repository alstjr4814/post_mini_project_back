package com.korit.post_mini_project_back.dto.request;

import lombok.Data;

@Data
public class GetFeedListReqDto {
    private int currentPage;
    private int size;
}