package Oracle.Partner.Tracker.infra.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Oracle.Partner.Tracker.entities.Partner;
import Oracle.Partner.Tracker.utils.Status;

public class UserAuthenticated implements UserDetails{

    public UserAuthenticated(Partner partner) {
        this.partner = partner;
    }

    private final Partner partner;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> partner.getRole());
    }

    @Override
    public String getPassword() {
        return partner.getPassword();
    }

    @Override
    public String getUsername() {
        return partner.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        if(partner.getStatus() == Status.ACTIVE){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
        //TODO: Implemntentar validação de conta bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //TODO: Implemntentar validação de credenciais expiradas
    }

    @Override
    public boolean isEnabled() {
        return true;
        //TODO: Implemntentar validação de conta desabilitada
    }

    
}
