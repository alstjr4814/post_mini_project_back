package com.korit.post_mini_project_back.mapper;

import com.korit.post_mini_project_back.entity.Comment;
import com.korit.post_mini_project_back.entity.CustomComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
    List<CustomComment> findAllCommentByPostId(@Param("postId") int postId);
}
