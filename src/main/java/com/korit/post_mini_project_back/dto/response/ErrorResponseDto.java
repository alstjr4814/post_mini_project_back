package com.korit.post_mini_project_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponseDto {
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}