package com.ecommerce.orders.infraestructure.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecommerce.orders.infraestructure.security.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;


public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwtToken != null ) {
            jwtToken = jwtToken.substring(7);

            DecodedJWT decodedJWT = jwtUtils.verifyToken(jwtToken);

            String username = jwtUtils.getUsernameFromToken(decodedJWT);
            String authorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();


            Collection<? extends GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }
}
