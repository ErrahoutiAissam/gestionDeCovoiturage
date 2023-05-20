package com.example.gestionDeCovoiturage.security;

import com.example.gestionDeCovoiturage.models.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtUtils {
   @Value("${jwt.secret}")
   private String JWT_SECRET_KEY;

   @Value("${jwt.access-expiration-duration-sec}")
   private int ACCESS_TOKEN_EXPIRATION_DURATION_SECONDE;

   @Value("${jwt.refresh-expiration-duration-sec}")
   private int REFRESH_TOKEN_EXPIRATION_DURATION_SECONDE;

   public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   public Date extractExpirationDate(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
   }

   private Claims extractAllClaims(String token) {
      return Jwts
              .parser()
              .setSigningKey(JWT_SECRET_KEY)
              .parseClaimsJws(token)
              .getBody();
   }

   private Boolean isTokenExpired(String token) {
      return extractExpirationDate(token).before(new Date());
   }

   public String generateAccessToken(Utilisateur user) {
      final Optional<String> optionalAuthority = user.getAuthorities()
              .stream()
              .map(GrantedAuthority::getAuthority)
              .findFirst();

      Map<String, Object> claims = new HashMap<>();
      claims.put("id", user.getId());
      claims.put("nom", user.getNom());
      claims.put("prenom", user.getPrenom());
      if(user.getImage() != null) {
         claims.put("image", user.getImage().getUrl());
      }
      optionalAuthority.ifPresent(role -> claims.put("authority", role));
      return createToken(claims, ACCESS_TOKEN_EXPIRATION_DURATION_SECONDE, user);
   }

   public String generateRefreshToken(Utilisateur user) {
      Map<String, Object> claims = new HashMap<>();
      claims.put("id", user.getId());
      return createToken(claims, REFRESH_TOKEN_EXPIRATION_DURATION_SECONDE, user);
   }

   public Boolean validateToken(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

   public String createToken(Map<String, Object> claims, int secondes, Utilisateur user) {
      final long currentTime = System.currentTimeMillis();
      return Jwts.builder()
              .setClaims(claims)
              .setSubject(user.getUsername())
              .setIssuedAt(new Date(currentTime))
              .setExpiration(new Date(currentTime + TimeUnit.SECONDS.toMillis(secondes)))
              .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
              .compact();
   }
}