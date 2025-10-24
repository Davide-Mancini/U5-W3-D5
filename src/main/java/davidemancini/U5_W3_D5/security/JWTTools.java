package davidemancini.U5_W3_D5.security;

import davidemancini.U5_W3_D5.entities.User;
import davidemancini.U5_W3_D5.exceptions.MyUnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTTools {
    @Value("${jwt.secret}")//LEGGO IL SEGRETO DAL APPLICATION PROPERTIES
    private String segreto;

    public String createToken(User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))//DATA EMISSIONE
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//DATA DI SCADENZA A 24 ORE
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(segreto.getBytes()))
                .compact();
    }

    public void verificaToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(segreto.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new MyUnauthorizedException("Errori nel token");
        }
    }

    public UUID idFromToken(String token) {
        return UUID.fromString(Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(segreto.getBytes())).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());
    }
}
