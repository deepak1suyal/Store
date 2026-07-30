package com.store.store.Jwt;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.crypto.SecretKey;
@Component
@SuppressWarnings("deprecation")
public class Jwt {
    public static final String SECRET = "ThisisaSecretKeyThisisaSecretKeyThisisaSecretKey";
    public static final long EXPIRATION_TIME = 60*60*24000; // 1 day in milliseconds
    public static final SecretKey SECRET_KEY=Keys.hmacShaKeyFor(SECRET.getBytes());//Changing SCRIPT to byte code
//Decoding
    public Claims extractAllClains(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token)
    {
        return extractAllClains(token).getSubject();
    }

    public boolean isValidToken(String token,String Username)
    {
        return Username.equals(extractUsername(token)) && (new Date().before(extractExpirey(token)));
    }
      

public Date extractExpirey(String token)
{
    return extractAllClains(token).getExpiration();
}
//Encoding
public  String generateToken(String username)
{
    return Jwts.builder().signWith(SignatureAlgorithm.HS256,SECRET_KEY).addClaims(new HashMap<>()).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)).compact();
}

// public static void main(String[] args) {
//     UserDetails UD=new User("Deepak", "root",Set.of(new SimpleGrantedAuthority("Admin")));
//     Jwt jwt=new Jwt();
//     String token=jwt.generateToken(UD.getUsername());
//     System.out.println("Token :"+token);
//     System.out.println("Username :"+jwt.extractUsername(token));
//     System.out.println("Expirey :"+DateFormatUtils.format(jwt.extractExpirey(token), "dd-MM-yyyy HH:mm:ss"));
//     System.out.println("isValid :"+jwt.isValidToken(UD.getUsername(), token));
// }
}

