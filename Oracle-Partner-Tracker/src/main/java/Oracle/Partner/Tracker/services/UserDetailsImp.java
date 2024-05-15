package Oracle.Partner.Tracker.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import Oracle.Partner.Tracker.entities.Partner;
import Oracle.Partner.Tracker.infra.security.UserAuthenticated;
import Oracle.Partner.Tracker.repositories.PartnerRepository;

public class UserDetailsImp implements UserDetailsService{

    private final PartnerRepository partnerRepository;
    public UserDetailsImp(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Partner partnerToLog = partnerRepository.findByUsername(username);
        if (partnerToLog != null) {
            return new UserAuthenticated(partnerToLog);
        }
        throw new UsernameNotFoundException("User not found");
    }
    
}
