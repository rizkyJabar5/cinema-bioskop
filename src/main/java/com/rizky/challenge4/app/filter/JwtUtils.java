package com.rizky.challenge4.app.filter;

import com.rizky.challenge4.app.HasLogger;
import com.rizky.challenge4.backend.model.entity.Users;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtUtils implements HasLogger {


    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private String jwtExpirationMs;

    public String generateJwtToken(Authentication auth) {

        Users userPrincipal = (Users) auth.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            getLogger().error("Invalid JWT Signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            getLogger().error("Invalid JWT Token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            getLogger().error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            getLogger().error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            getLogger().error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
