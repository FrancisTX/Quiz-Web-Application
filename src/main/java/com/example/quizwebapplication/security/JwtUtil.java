package com.example.quizwebapplication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Value("$security.jwt.token.key")
    private String key;


    // create jwt from a UserDetail
    public String createToken(AuthUserDetail userDetails){
        //Claims is essentially a key-value pair, where the key is a string and the value is an object
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername()); // user identifier
        System.out.println("username @createToken in util: " + userDetails.getUsername());
        claims.put("permissions", userDetails.getAuthorities()); // user permission
        claims.put("custom", Collections.singletonMap("name", userDetails.getFullName()));
        claims.put("userId", userDetails.getId()); // Add userId as a claim

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key) // algorithm and key to sign the token
                .compact();
    }

    public Optional<AuthUserDetail> resolveToken(HttpServletRequest request) throws SignatureException {
        String prefixedToken = request.getHeader("Authorization"); // extract token value by key "Authorization"
        if(prefixedToken == null || !prefixedToken.startsWith("Bearer ")){
            System.out.println("Token is null or does not start with Bearer");
            return Optional.empty();
        }
        String token = prefixedToken.substring(7); // remove the prefix "Bearer "
        System.out.println("token @resolveToken in util: " + token);

        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // decode
        }catch (SignatureException e){
            System.out.println("Invalid JWT signature");
            throw new SignatureException("Invalid JWT signature");
        }

        String username = claims.getSubject();
        List<LinkedHashMap<String, String>> permissions = (List<LinkedHashMap<String, String>>) claims.get("permissions");
        System.out.println("permissions @resolveToken in util: " + permissions);

        // convert the permission list to a list of GrantedAuthority
        List<GrantedAuthority> authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.get("authority")))
                .collect(Collectors.toList());

        System.out.println("authorities @resolveToken in util: " + authorities);

        //return a userDetail object with the permissions the user has
        return Optional.of(AuthUserDetail.builder()
                .username(username)
                .authorities(authorities)
                .build());
    }
}
