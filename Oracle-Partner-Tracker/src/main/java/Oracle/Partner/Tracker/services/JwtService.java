package Oracle.Partner.Tracker.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    public List<String> generateToken(AuthDTO authentication) {

        List<String> response = null;

        Instant now = Instant.now();
        long exp = 3600L;

        Partner partner = partnerRepository.findByEmail(authentication.email());
        //String scopes = authentication.getAuthorities().stream()
        @SuppressWarnings("static-access")
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

        response = Arrays.asList(
            jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(),
            partner.getUsername(),
            partner.getRole().name());

        return response;
    }
}
