package com.poly.fman.config.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.poly.fman.service.common.CookieService;
import com.poly.fman.service.common.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final CookieService cookieService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().contains("/auth")) {
            request.getSession().setAttribute("jwtFiltetValided", false);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = cookieService.getValue("token");
        String jwt;
        String username;
        if (authHeader == null || authHeader.equals("")) {
            request.getSession().setAttribute("jwtFiltetValided", false);
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader;
        username = jwtService.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails;
            try {
                userDetails = this.userDetailsService.loadUserByUsername(username);
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/auth/logout");
                return;
            }

            if (cookieService.getValue("remember").equals("") ? jwtService.isTokenValid(jwt, userDetails)
                    : jwtService.isTokenValid(jwt, userDetails, true)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                request.getSession().setAttribute("jwtFiltetValided", true);
            }
        }
        filterChain.doFilter(request, response);

    }

}