package com.korit.post_mini_project_back.service;

import com.korit.post_mini_project_back.dto.request.CreatePostReqDto;
import com.korit.post_mini_project_back.dto.request.GetFeedListReqDto;
import com.korit.post_mini_project_back.dto.response.PaginationRespDto;
import com.korit.post_mini_project_back.entity.ImageFile;
import com.korit.post_mini_project_back.entity.Post;
import com.korit.post_mini_project_back.entity.User;
import com.korit.post_mini_project_back.mapper.ImageFileMapper;
import com.korit.post_mini_project_back.mapper.PostMapper;
import com.korit.post_mini_project_back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final FileService fileService;
    private final PostMapper postMapper;
    private final ImageFileMapper imageFileMapper;

    @Transactional(rollbackFor = Exception.class)
    public void createPost(CreatePostReqDto dto) {
        List<ImageFile> files = fileService.upload("post", dto.getFiles());
        Post post = dto.toEntity();
        postMapper.insert(post);
        if (files == null) {
            return;
        }
        files.forEach(file -> file.setReferenceId(post.getPostId()));
        imageFileMapper.insertToMany(files);
    }

    public PaginationRespDto<Post> getFeeds(GetFeedListReqDto dto) {
        int size = dto.getSize();
        int startIndex = (dto.getCurrentPage() - 1) * size;
        User user = PrincipalUser.getAuthenticatedPrincipalUser().getUser();
        List<Post> feeds = postMapper.getFeeds(startIndex, size, user.getUserId());
        int totalElements = postMapper.getTotalCount(user.getUserId());
        int totalPages = (int) Math.ceil(((double) totalElements) / size);
        return PaginationRespDto.<Post> builder()
                .contents(feeds)
                .currentPage(dto.getCurrentPage())
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .isLast(dto.getCurrentPage() == totalPages)
                .build();
    }
}









