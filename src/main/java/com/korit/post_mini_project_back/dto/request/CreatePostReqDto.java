package com.korit.post_mini_project_back.dto.request;

import com.korit.post_mini_project_back.entity.Post;
import com.korit.post_mini_project_back.security.PrincipalUser;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreatePostReqDto {
    private String visibility;
    private String content;
    private List<MultipartFile> files;

    public Post toEntity() {
        int userId = PrincipalUser.getAuthenticatedPrincipalUser().getUser().getUserId();

        return Post.builder()
                .content(content)
                .visibility(visibility)
                .userId(userId)
                .build();
    }
}










