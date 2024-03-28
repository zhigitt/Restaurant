package java12.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import java12.entities.User;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.ZonedDateTime;

@Service
public class JwtService {

//    @Value("{app.jwt.secret}")
    private final String secretKey =  "zhigit";

    // save jwt token  // encode token
    public String createToken(User user) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        return JWT.create()
                .withClaim("email", user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole().name())
                .withIssuedAt(ZonedDateTime.now().toInstant())
                .withExpiresAt(ZonedDateTime.now().plusHours(24).toInstant())
                .sign(algorithm);
    }

    // verify token     // decode token
    public String verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        return decodedJWT.getClaim("email").asString();
    }
}
