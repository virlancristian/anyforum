package net.anyforum.backend.util.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.anyforum.backend.models.api.auth.AuthJwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil {
    private String secret;

    public TokenUtil(@Value("${app.datasource.jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(AuthJwtToken userToken) {
        return Jwts
                .builder()
                .setSubject(userToken.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
