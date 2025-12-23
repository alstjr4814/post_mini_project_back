package com.korit.post_mini_project_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRespDto<T> {
    private List<T> contents;
    private int totalElements;
    private int totalPages;
    private int currentPage;
    private int size;
    private boolean isLast;
}