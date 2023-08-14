package com.poly.fman.config.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.poly.fman.config.filter.SessionDataFilter;
import com.poly.fman.service.AuthenticationServiceImlp;
import com.poly.fman.service.CustomOAuth2UserService;
import com.poly.fman.service.OAuth2SuccessHandler;
import com.poly.fman.service.UserService;
import com.poly.fman.service.common.AuthenticationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final SessionDataFilter sessionDataFilter;
        private final AuthenticationProvider authenticationProvider;
        private final CustomOAuth2UserService customOAuth2UserService;
        private final OAuth2SuccessHandler oAuth2SuccessHandler;

        /**
         * Filter chain to configure security.
         * 
         * @param http The security object.
         * @return The chain built.
         * @throws Exception Thrown on error configuring.
         */
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                // TODO: Proper authentication.
                http.csrf().disable().cors().disable();
                http
                                .authorizeHttpRequests()
                                .requestMatchers(
                                                "/user/**")
                                .hasRole("USER")
                                .requestMatchers(
                                                "/admin/**",
                                                "/fman")
                                .hasAnyRole("ADMIN", "STAFF")
                                .anyRequest()
                                .permitAll()
                                .and()
                                .formLogin(login -> login
                                                .loginPage("/auth/account"))
                                .oauth2Login(login -> login
                                                .loginPage("/login")
                                                .userInfoEndpoint()
                                                .userService(customOAuth2UserService)
                                                .and().successHandler(oAuth2SuccessHandler))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .addFilterAfter(sessionDataFilter, JwtAuthenticationFilter.class);
                // .exceptionHandling()
                // .accessDeniedPage("/auth/account")
                // .exceptionHandling().accessDeniedHandler(new AccessDeniesHander());

                return http.build();
        }

}