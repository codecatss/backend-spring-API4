package Oracle.Partner.Tracker.services;

import java.text.Collator;
import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Autowired
    private JwtEncoder jwtEncoder;
    
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long exp = 3600L;

        String scopes = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
            .issuer("Oracle-Partner-Tracker")
            .issuedAt(now)
            .expiresAt(Instant.ofEpochSecond(now.getEpochSecond() + exp))
            .subject(authentication.getName())
            .claim("scope", scopes)
            .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
