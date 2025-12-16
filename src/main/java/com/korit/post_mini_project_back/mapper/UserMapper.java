package com.korit.post_mini_project_back.mapper;

import com.korit.post_mini_project_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insert(User user);
    User findByUserId(int userId);
    User findByOauth2Id(String oauth2Id);
    User findByNickname(String nickname);
    String createNickname();
}
