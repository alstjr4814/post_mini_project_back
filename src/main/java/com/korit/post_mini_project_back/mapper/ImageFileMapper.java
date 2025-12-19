package com.korit.post_mini_project_back.mapper;

import com.korit.post_mini_project_back.entity.ImageFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageFileMapper {
    int insertToMany(List<ImageFile> files);
}