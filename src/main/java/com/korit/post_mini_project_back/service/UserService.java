package com.korit.post_mini_project_back.service;

import com.korit.post_mini_project_back.entity.User;
import com.korit.post_mini_project_back.mapper.UserMapper;
import com.korit.post_mini_project_back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public User findUserByOauth2Id(String oauth2Id) {
        return userMapper.findByOauth2Id(oauth2Id);
    }

    public User createUser(Authentication authentication) {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        User user = principalUser.getUser();
        user.setNickname(createNickname());
        userMapper.insert(user);
        return user;
    }

    public String createNickname() {
        String newNickname = null;
        while (true) {
            newNickname = userMapper.createNickname();
            if (userMapper.findByNickname(newNickname) == null) {
                break;
            }
        }
        return newNickname;
    }
}
