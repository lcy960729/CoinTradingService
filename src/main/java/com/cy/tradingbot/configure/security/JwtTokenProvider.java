package com.cy.tradingbot.configure.security;

import com.cy.tradingbot.domain.user.service.UserService;
import com.cy.tradingbot.dto.user.UserDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final UserService userService;

    public JwtTokenProvider(UserService userService) {
        this.userService = userService;
    }

    public String createToken(UserDTO userDTO) {
        Claims claims = Jwts.claims().setSubject(userDTO.getUsername());
        claims.put("role", userDTO.getAuthority());

        Date now = new Date();

        final long expiredTime = 30 * 60 * 1000L;

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiredTime))
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();

        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String jwtToken) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        try {
            Jws<Claims> claims = jwtParser.parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
