package com.korit.post_mini_project_back.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String clientName = userRequest.getClientRegistration().getClientName();


        Collection <? extends GrantedAuthority> authorities = oAuth2User.getAuthorities();
        Map<String,Object> attributes = new LinkedHashMap<>();
        String nameAttributekey = null;

        if ("NAVER".equalsIgnoreCase(clientName)){
            Map<String, Object> response = (Map<String, Object>) oAuth2User.getAttributes().get("response");
            attributes.putAll(response);
            nameAttributekey= "id";
        }

        return new DefaultOAuth2User(authorities,attributes,nameAttributekey);
    }
}
