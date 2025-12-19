package com.korit.post_mini_project_back.controller;

import com.korit.post_mini_project_back.dto.request.CreatePostReqDto;
import com.korit.post_mini_project_back.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPost(@ModelAttribute CreatePostReqDto dto) {
        System.out.println(dto);
        postService.createPost(dto);
        return ResponseEntity.ok(null);
    }
}