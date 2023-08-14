package com.poly.fman.config.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oauth2User;

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();

    }

    @Override
    public String getName() {
        return oauth2User.<String>getAttribute("name");

    }

    public String getEmail() {
        return oauth2User.<String>getAttribute("email");
    }

    public String getImage() {
        return oauth2User.<String>getAttribute("picture");
    }

}
