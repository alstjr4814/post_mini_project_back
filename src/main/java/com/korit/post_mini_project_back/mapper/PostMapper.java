package com.korit.post_mini_project_back.mapper;

import com.korit.post_mini_project_back.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    int insert(Post post);
    List<Post> getFeeds(
            @Param("startIndex") int startIndex,
            @Param("size") int size,
            @Param("userId") int userId);
    int getTotalCount(@Param("userId") int userId);
}