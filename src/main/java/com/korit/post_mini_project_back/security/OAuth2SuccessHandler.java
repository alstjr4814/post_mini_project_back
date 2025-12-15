package com.korit.post_mini_project_back.security;

import com.korit.post_mini_project_back.entity.User;
import com.korit.post_mini_project_back.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication);

        // 데이터베이스에서 User 정보 조회
        User foundUser = User.builder()
                .userId(10)
                .build();

        if (foundUser == null) {
            //회원가입
        }
        String accessToken = jwtTokenProvider.createAccessToken(foundUser);
        response.sendRedirect("http://localhost:5173/auth/login/oauth2?accessToken=" + accessToken);
    }
}
