package io.github.fabiocintra.event_management.security;

import io.github.fabiocintra.event_management.user.UserService;
import io.github.fabiocintra.event_management.user.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterJWTAuthentication extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtDecoder jwtDecoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            authentication = getAuthentication(request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = getTokenFromTheCookie(request);
        if (token == null) {
            return null;
        }
        Jwt decoded = jwtDecoder.decode(token);
        String username = decoded.getSubject();
        User user = userService.findUserByEmail(username);
        if (user == null) { // caso de para burlar colocando um subject que nao existe
            return null;
        }
        return new CustomAuthentication(user);
    }

    private String getTokenFromTheCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("ACCESS_TOKEN")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
