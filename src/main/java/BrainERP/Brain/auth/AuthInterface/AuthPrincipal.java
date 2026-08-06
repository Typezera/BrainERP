package BrainERP.Brain.auth.AuthInterface;

import BrainERP.Brain.user.model.UserOrCompany;

public interface AuthPrincipal {
    Long getId();
    String getEmail();
    UserOrCompany getHowAreYou();
}
