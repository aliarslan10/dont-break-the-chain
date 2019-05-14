package com.example.aliarslan.dontbreakthechain.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JWTConfig.HEADER_STRING);

        if (header != null && header.startsWith(JWTConfig.TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authenticationToken = isTokenValid(request);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request,response);
        } else {
            chain.doFilter(request,response);
            return;
        }
    }


    private UsernamePasswordAuthenticationToken isTokenValid(HttpServletRequest request) {
        String token = request.getHeader(JWTConfig.HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(JWTConfig.SECRET_KEY.getBytes())
                    .parseClaimsJws(token.replace(JWTConfig.TOKEN_PREFIX,""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }

}
