package BrainERP.Brain.auth.security;

import BrainERP.Brain.auth.AuthInterface.AuthPrincipal;
import BrainERP.Brain.user.model.UserModel;
import BrainERP.Brain.user.model.UserOrCompany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails, AuthPrincipal {

    private final UserModel user;

    public SecurityUser(UserModel userModel){
        this.user = userModel;
    }

    @Override
    public String getUsername(){
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isActivate();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public UserOrCompany getAccountType() {
        return user.getHowAreYou();
    }
}
