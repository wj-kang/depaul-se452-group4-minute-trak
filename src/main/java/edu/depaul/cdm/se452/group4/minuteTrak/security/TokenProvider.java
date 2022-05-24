package edu.depaul.cdm.se452.group4.minuteTrak.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;
import edu.depaul.cdm.se452.group4.minuteTrak.model.AdminEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
  private static final String SECRET_KEY = "secretkey"; // ***** TEMPORARY TOKEN SECRET

  public String createEmployeeToken(EmployeeEntity employeeEntity) {
    // Valid for a day
    Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
    log.info("===> create a new token for eId: {}", employeeEntity.getEId());

    return Jwts.builder()//
        .signWith(SignatureAlgorithm.HS512, TokenProvider.SECRET_KEY)//
        .claim("id", employeeEntity.getEId())//
        .claim("role", "employee")//
        .setIssuer("minutetrak")//
        .setIssuedAt(new Date())//
        .setExpiration(expiryDate)//
        .compact();
  }

  public String createAdminToken(AdminEntity adminEntity) {
    // Valid for a day
    Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
    log.info("===> create a new admin token for : {}", adminEntity.getAdminId());

    return Jwts.builder()//
        .signWith(SignatureAlgorithm.HS512, TokenProvider.SECRET_KEY)//
        .claim("id", adminEntity.getAdminId())//
        .claim("role", "admin")//
        .setIssuer("minutetrak")//
        .setIssuedAt(new Date())//
        .setExpiration(expiryDate)//
        .compact();
  }

  public AuthStatus validateAndGetAuthStatus(String token) {
    Claims claims = Jwts.parser()//
        .setSigningKey(TokenProvider.SECRET_KEY)//
        .parseClaimsJws(token)//
        .getBody();

    return new AuthStatus(claims.get("id").toString(), claims.get("role").toString());
  }
}


