package com.ecom.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get the token from header
        //Authorization = Bearer 23456789.23456789.23456789
        String requestToken = request.getHeader("Authorization");
        logger.info("message", requestToken);

        String userName = null;
        String jwtToken = null;

        if(requestToken !=null && requestToken.startsWith("Bearer")) {
            jwtToken = requestToken.substring(7);


            //get userName from user
            try {
                userName = this.jwtHelper.getUsernameFromToken(jwtToken);
            } catch (ExpiredJwtException exception) {
                logger.info("message", "Jwt Token Expired..!");
            } catch (MalformedJwtException exception) {
                logger.info("message", "Invalid Jwt Token..!");
            } catch (IllegalArgumentException exception) {
                logger.info("message", "Unable To Get Token..!");
            }

            //validate token
            if (userName != null && SecurityContextHolder.getContext()==null)
            {
                //validate
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
                if(this.jwtHelper.validateToken(jwtToken, userDetails)){
                    //authentication set

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
                else {
                    logger.info("Not validated message", "Invalid Jwt Token..!");
                }
            }
            else {
                logger.info("userName message", "userName is null or auth is already there..!");
            }
        } else {
            logger.info("token message", "token does not starts with bearer..!");
        }

        filterChain.doFilter(request, response);
    }


}
