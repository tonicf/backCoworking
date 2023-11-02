package com.aytocarmona.coworking.v1.security.authentication;

import com.aytocarmona.coworking.v1.dto.UserDto;
import com.aytocarmona.coworking.v1.security.constants.SecurityConstants;
import com.aytocarmona.coworking.v1.security.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * Attempts to authenticate the user based on the provided credentials.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return An Authentication object representing the authenticated user.
     * @throws AuthenticationException if authentication fails.
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(SecurityConstants.METHOD_POST)) {
            throw new AuthenticationServiceException(request.getMethod());
        } else {
            UserDto authCredentials = new UserDto();

            try {
                authCredentials = new ObjectMapper().readValue(request.getReader(), UserDto.class);
            } catch (IOException e) {
                return null;
            }

            UsernamePasswordAuthenticationToken userNamePATH = new UsernamePasswordAuthenticationToken(
                    authCredentials.getDni(),
                    authCredentials.getPassword(),
                    Collections.emptyList()
            );

            return getAuthenticationManager().authenticate(userNamePATH);
        }
    }

    /**
     * Handles the successful authentication of the user.
     *
     * @param request    The HTTP request.
     * @param response   The HTTP response.
     * @param chain      The filter chain.
     * @param authResult The Authentication object representing the authenticated user.
     * @throws IOException      if an I/O error occurs.
     * @throws ServletException if a servlet error occurs.
     */

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();
        String token = JWTUtil.createToken(userDetailsImpl.getId(), userDetailsImpl.getDni());

        PrintWriter writer = response.getWriter();
        response.setContentType(SecurityConstants.APP_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put(SecurityConstants.TOKEN_JSON, token);
        String jsonResponse = jsonObject.toString();
        writer.write(jsonResponse);
        writer.flush();
    }
}

