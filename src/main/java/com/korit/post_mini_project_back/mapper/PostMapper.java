package com.korit.post_mini_project_back.mapper;

import com.korit.post_mini_project_back.entity.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    int insert(Post post);

}