package ra.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ra.security.userPrincipal.UserPrincipal;

import java.util.Date;

@Component
public class JwtProvider {

    private Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Value("${expired}")
    private Long EXPIRED;
    @Value("${secret_key}")
    private String SECRET_KEY;

    public String generateToken(UserPrincipal userPrincipal) {
        String token = Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // subject là để phần được token của người nào vì username là duy nhất ko bị trùng lặp
                .setIssuedAt(new Date()) // thời gian bắt đầu của token
                .setExpiration(new Date(new Date().getTime() + EXPIRED)) // thời gian hết hạn cuả token
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // chữ ký và chuối mã đặc biệt
                .compact();
        return token;
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            logger.error("Expired Token {}", expiredJwtException.getMessage());
        } catch (MalformedJwtException malformedJwtException) {
            logger.error("Invalid format {}", malformedJwtException.getMessage());
        } catch (UnsupportedJwtException unsupportedJwtException) {
            logger.error("Unsupported token {}", unsupportedJwtException.getMessage());
        } catch (SignatureException signatureException) {
            logger.error("Invalid Signature token {}", signatureException.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
