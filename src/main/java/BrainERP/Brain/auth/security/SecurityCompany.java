package BrainERP.Brain.auth.security;

import BrainERP.Brain.auth.AuthInterface.AuthPrincipal;
import BrainERP.Brain.company.model.CompanyModel;
import BrainERP.Brain.user.model.UserOrCompany;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityCompany implements UserDetails, AuthPrincipal {
    private final CompanyModel company;

    public SecurityCompany(CompanyModel company){
        this.company = company;
    }

    @Override
    public String getUsername(){
        return company.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return company.isActivateCompany();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return company.getPassword();
    }

    @Override
    public Long getId() {
        return company.getId();
    }

    @Override
    public String getEmail() {
        return company.getEmail();
    }

    @Override
    public UserOrCompany getAccountType() {
        return company.getAccountType();
    }
}
