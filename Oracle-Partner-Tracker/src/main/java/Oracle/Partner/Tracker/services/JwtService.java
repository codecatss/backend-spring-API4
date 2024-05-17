package Oracle.Partner.Tracker.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.AuthDTO;
import Oracle.Partner.Tracker.entities.Partner;
import Oracle.Partner.Tracker.repositories.PartnerRepository;

@Service
public class JwtService {
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    PartnerRepository partnerRepository;
    
    public String generateToken(AuthDTO authentication) {
        Instant now = Instant.now();
        long exp = 3600L;

        Partner partner = partnerRepository.findByEmail(authentication.email());
        //String scopes = authentication.getAuthorities().stream()
        String scopes = Arrays.stream(partner.getRole().values())
            .map(Enum::name)
            .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
            .issuer("Oracle-Partner-Tracker")
            .issuedAt(now)
            .expiresAt(Instant.ofEpochSecond(now.getEpochSecond() + exp))
            .subject(partner.getUsername())
            .claim("scope", scopes)
            .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
