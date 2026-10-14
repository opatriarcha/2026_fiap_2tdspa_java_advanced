package br.com.fiap.tds.tdspa.javaadv.blogBackend.infrastructure.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtHelper {

    private final String SECRET = "chave-secreta-super-secreta-extremamente-segura-e-secreta-de-novo";
    private final int EXPIRATION_MS = 86400000; // 24 horas.
    private final int REFRESH_TOKEN_EXPIRATION_MS = 604800000; //7 dias

    public String generateToken( UserDetails userDetails ){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration( new Date(System.currentTimeMillis() + this.EXPIRATION_MS))
                .signWith(this.getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String extractUsername( final String token ){
        return Jwts.parserBuilder()
                .setSigningKey(this.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenValid(final String token, UserDetails userdetails ){
        final String username = this.extractUsername(token);
        return username.equals(userdetails.getUsername()) && !this.isTokenExpired(token);
    }

    private boolean isTokenExpired( final String token ){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    private String generateRefreshToken( UserDetails userDetails ){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration( new Date(System.currentTimeMillis() + this.REFRESH_TOKEN_EXPIRATION_MS))
                .signWith(this.getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(this.SECRET.getBytes(StandardCharsets.UTF_8));
    }

}
