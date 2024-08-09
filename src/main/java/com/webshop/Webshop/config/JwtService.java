package com.webshop.Webshop.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "7A5A57527150524E4A714743446D5A6770446B5374536D736E4D57694E6C6168";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){

        return createToken(new HashMap<>(), userDetails);
    }

    public String createToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .claim("authorities",userDetails.getAuthorities())
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }


    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);

        return  (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return  extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
