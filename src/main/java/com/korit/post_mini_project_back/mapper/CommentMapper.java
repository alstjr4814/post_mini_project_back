package com.korit.post_mini_project_back.mapper;

import com.korit.post_mini_project_back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
}
