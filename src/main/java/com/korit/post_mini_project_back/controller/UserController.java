package com.korit.post_mini_project_back.controller;

import com.korit.post_mini_project_back.entity.User;
import com.korit.post_mini_project_back.security.PrincipalUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<User> getMe(@AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(principalUser.getUser());
    }



}