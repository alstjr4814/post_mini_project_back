package com.korit.post_mini_project_back.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class GetFeedListReqDto {
    private int currentPage;
    private int size;
}
