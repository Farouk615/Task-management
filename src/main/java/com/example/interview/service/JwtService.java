package com.example.interview.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static String SECRET_KEY="uHKUUDCdgxciF1ThESvOpbFR5oW9PJIGEG04FGA5xeykhNXMVxzIRCfgk1rK9mE8K6IWpHcL7iWBge91M5hzo4APHoV866PjHao3XvrgOFmMHecssVXToaVqXthK0uKMgP8CRxENqB8bxOAlEiT15eDOPmi12YeI3NGP7fPETUmtu8KvvFVVuCKXyUIaozHW44uS8BiLYXomJlLcKaIZKoF7mjfikOSs9saEujbEcbxwXMYn8ZY333F0tfN7gvTj";

    public static String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // generate token only from userDetails (no extra claims)
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    // generate token from userDetails (with extra claims if passed ofc)
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims((token));
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUserName(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

