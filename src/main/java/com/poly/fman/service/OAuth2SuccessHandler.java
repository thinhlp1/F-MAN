package com.poly.fman.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poly.fman.config.security.CustomOAuth2User;
import com.poly.fman.dto.auth.AuthResponseDTO;
import com.poly.fman.dto.auth.RegisterDTO;
import com.poly.fman.entity.AuthenticationProvider;
import com.poly.fman.entity.Role;
import com.poly.fman.entity.User;
import com.poly.fman.repository.UserRepository;
import com.poly.fman.service.common.CookieService;
import com.poly.fman.service.common.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final CookieService cookieService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    // private final AuthenticationManager authenticationManager;

    private HttpSession session;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        System.out.println("Đăng nhập thành công");
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication
                .getPrincipal();
        System.out.println(oauthUser.getAttributes());
        User user = userRepository.findByEmailAndActiveIsTrue(oauthUser.getEmail()).orElse(null);
        if (user == null) {
            System.out.println("Đăng ký");
            User userRegister = register(oauthUser);
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userRegister, // Người dùng đã đăng ký
                    null, // Mật khẩu không cần thiết
                    userRegister.getAuthorities() // Các quyền của người dùng
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            response.sendRedirect("/home");
        } else {
            if (user.getAuthenticationProvider().equals(AuthenticationProvider.LOCAL)) {
                System.out.println("Email này đã được đăng ký. Hãy đăng nhập");
                response.sendRedirect("/home");

            } else {
                System.out.println("Đăng nhập như bình thường");
                User userRegister = login(oauthUser);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        userRegister, // Người dùng đã đăng ký
                        null, // Mật khẩu không cần thiết
                        userRegister.getAuthorities() // Các quyền của người dùng
                );
                SecurityContextHolder.getContext().setAuthentication(auth);

                response.sendRedirect("/home");
            }
        }
    }

    public User login(CustomOAuth2User oauthUser) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        User user = userRepository
                .findByUsernameAndActiveIsTrueAndAuthenticationProviderEquals(oauthUser.getEmail(),
                        AuthenticationProvider.GOOGLE)
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        authResponseDTO = AuthResponseDTO.builder()
                .accessToken(jwtToken)
                .roleAccess(user.getRole().getName())
                .username(user.getUsername())
                .userId(user.getId())
                .build();
        cookieService.add("token", authResponseDTO.getAccessToken(), 24);
        cookieService.add("userId", authResponseDTO.getUserId() + "", 24);
        cookieService.add("username", authResponseDTO.getUsername(), 24);
        cookieService.remove("remember");
        session.setAttribute("userId", authResponseDTO.getUserId());
        return user;
    }

    public User register(CustomOAuth2User oauthUser) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        User user = new User();
        user.setPassword(passwordEncoder.encode(""));

        Role role = new Role();
        role.setId("USER");
        role.setName("User");
        user.setRole(role);

        user.setNumberPhone("");
        user.setName(oauthUser.getName());
        user.setEmail(oauthUser.getEmail());
        user.setUsername(oauthUser.getEmail());
        user.setImage(oauthUser.getImage());
        user.setCreateAt(new Date());
        user.setActive(true);

        user.setAuthenticationProvider(AuthenticationProvider.GOOGLE);

        System.out.println(user.toString());

        user = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        authResponseDTO = AuthResponseDTO.builder()
                .accessToken(jwtToken)
                .roleAccess(user.getRole().getName())
                .username(user.getUsername())
                .userId(user.getId())
                .build();
        cookieService.add("token", authResponseDTO.getAccessToken(), 24);
        cookieService.add("userId", authResponseDTO.getUserId() + "", 24);
        cookieService.add("username", authResponseDTO.getUsername(), 24);
        cookieService.remove("remember");
        session.setAttribute("userId", authResponseDTO.getUserId());
        return user;
    }

}
