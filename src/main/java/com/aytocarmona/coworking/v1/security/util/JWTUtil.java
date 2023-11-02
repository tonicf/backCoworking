package com.aytocarmona.coworking.v1.security.util;

import com.aytocarmona.coworking.v1.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;

public class JWTUtil {
    /**
     * Creates a JWT token based on the provided user ID, DNI, and role.
     *
     * @param id  The user ID.
     * @param dni The user's DNI.
     * @return The generated JWT token.
     */
    public static String createToken(Long id, String dni) {
        Date expirationDate = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        if (!dni.isEmpty() && id != null) {
            Claims claims = Jwts.claims().setSubject(dni);
            claims.put("id", id);
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY.getBytes())
                    .compact();
        } else {
            return null;
        }
    }

    /**
     * Retrieves the authentication information from the provided JWT token.
     *
     * @param token The JWT token.
     * @return An instance of UsernamePasswordAuthenticationToken with the user's information.
     * @throws JwtException If the token is invalid or unable to parse.
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) throws JwtException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET_KEY.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String dni = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(dni, null, Collections.emptyList());
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT token", e);
        }
    }
}
