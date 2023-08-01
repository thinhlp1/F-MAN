package com.poly.fman.config.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.poly.fman.dto.model.CartDTO;
import com.poly.fman.entity.User;
import com.poly.fman.service.CartService;
import com.poly.fman.service.OrderService;
import com.poly.fman.service.UserService;
import com.poly.fman.service.common.CookieService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessionDataFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;
    private final CookieService cookieService;
    private final HttpSession httpSession;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Boolean jwtFilterValided = (Boolean) httpSession.getAttribute("jwtFiltetValided");
        if (!jwtFilterValided || jwtFilterValided == null) {
            filterChain.doFilter(request, response);
            return;
        } 

        String username = cookieService.getValue("username");

        try {
            User user = userService.getUser(username);
            String role = user.getRole().getId();
            if (role.equals("USER")) {

                httpSession.setAttribute("userId", user.getId());

            } else {
                int quantityOrderApprove = orderService.getOrderApproveQuantity();
                httpSession.setAttribute("username", user.getUsername());
                httpSession.setAttribute("userId", user.getId());
                httpSession.setAttribute("quantityOrderApprove", quantityOrderApprove);


            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        filterChain.doFilter(request, response);
        return;

    }

}