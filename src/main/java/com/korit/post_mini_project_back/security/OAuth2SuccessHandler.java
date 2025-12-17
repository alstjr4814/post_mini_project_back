package com.korit.post_mini_project_back.security;

import com.korit.post_mini_project_back.entity.User;
import com.korit.post_mini_project_back.jwt.JwtTokenProvider;
import com.korit.post_mini_project_back.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    // 콜백메스라서 클래스 없음 여기에 주의 ! warning!(민아)
    // 로그인 성공시 자동으로 실행되는 메서드라 사용되는 위치 찾을 수 없음( 콜백 메서드 )
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication);
//        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
//        oAuth2User.getAttributes().get("id");
        User foundUser = userService.finduserByOauth2Id(authentication.getName());

        if (Objects.isNull(foundUser)) {
            // 회원가입
            foundUser = userService.createUser(authentication);
        }
        String accessToken = jwtTokenProvider.createAccessToken(foundUser);
        response.sendRedirect("http://localhost:5173/auth/login/oauth2?accessToken=" + accessToken);
    }

}