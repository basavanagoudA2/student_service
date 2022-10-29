package com.bm.world.utilts;

import com.bm.world.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Log4j2
public class JwtUtils {
    @Value("${bm.jwt.secretKey}")
    private String jwtSecretKey;
    @Value(("${bm.jwt.expireMs}"))
    private int jwtExpireMs;
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
    public String generateJwtToken(Authentication authentication) throws ParseException {
       UserDetailsImpl userDetails =(UserDetailsImpl) authentication.getPrincipal();
        LocalDateTime currentDateTime=LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        log.info("Issued at: {}", currentDateTime);
        log.info("Expires at: {}", "d");
        return Jwts.builder()
               .setSubject(userDetails.getUsername())
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
               .signWith(SignatureAlgorithm.HS512,jwtSecretKey)
               .compact();
    }
    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            log.error("Invalid Jwt signature:[{}]"+e.getMessage());
        }catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
