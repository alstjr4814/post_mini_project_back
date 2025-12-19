package com.korit.post_mini_project_back.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponseDto {
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;

}
