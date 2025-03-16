package org.example.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.example.entities.UserEntity;
import org.example.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserRoleRepository userRoleRepository;
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String generateAccessToken(UserEntity user) {
        var roles = userRoleRepository.findByUser(user);
        Date currentDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);

        SecretKey key = getSecretKey();

        return Jwts.builder()
                .subject(format("%s,%s", user.getId(), user.getUsername()))
                .claim("email", user.getUsername())
                .claim("roles", roles.stream()
                        .map(role -> role.getRole().getName())
                        .toArray(String[]::new))
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(key)
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserId(String token) {
        return parseClaims(token).getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        return parseClaims(token).getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        return parseClaims(token).getExpiration();
    }

    public boolean validate(String token) {
        try {
            SecretKey key = getSecretKey();
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException ex) {
            System.out.println("Invalid JWT token - " + ex.getMessage());
        }
        return false;
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}