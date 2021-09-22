package de.sunbook.api.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
This class provides the creation, handling and validation of the JSON Web Token (JWT) for the User 
*/
@Service
public class JwtUtil {
   
    //Secret Key to decode or encode the JWT
    private String SECRET_KEY = "B69F6729D677987872B69EA685EB9";
    
    //this is the Expiration date of every JWT
    private int EXPIRATION = 1000 * 60 * 60 * 10;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Set<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority())
                .collect(Collectors.toSet());
        Map<String, Object> claims = new HashMap<>();
        for (String string : roles) {
            if (string.indexOf("ROLE_") > -1) {
                claims.put("role", string.substring(5));
            }
        }
        return createToken(claims, userDetails.getUsername());
    }

    //creates the JWT with the Claims, the expiration date and sign it with the SECRET_KEY
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //validates the JWT
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
