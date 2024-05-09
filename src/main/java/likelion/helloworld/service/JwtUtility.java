package likelion.helloworld.service;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtility {

    private String secret = "yourSecretKey";


    private static final long EXPIRATION_TIME = 1000L * 60 * 60;


    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes(StandardCharsets.UTF_8))
                .compact();

    }



    public Claims validateToken(String token) {
        try{

            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();

            return claims;

        } catch (SignatureException ex){

            System.out.println("Invalid JWT signature");
        }catch (ExpiredJwtException ex){

            System.out.println("Expired JWT token");
        }catch (Exception ex){

            System.out.println("Invalid JWT token");
        }
        return null;
    }



}
