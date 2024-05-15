package Oracle.Partner.Tracker.infra.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.utils.Status;

public class UserAuthenticated implements UserDetails{

    public UserAuthenticated(User user) {
        this.user = user;
    }

    private final User user;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole().name());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        if(user.getStatus() == Status.ACTIVE){
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
