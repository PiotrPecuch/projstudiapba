package com.projpba.projpba.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    @Value("${app.jwt-secret}")
    String secretKey;


    public String getNameFromToken(String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Bledny token");
        } catch (Exception e) {
            throw new IllegalArgumentException("Blad autoryzacji " + e);
        }
    }

}
