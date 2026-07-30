package BrainERP.Brain.auth.dto;

import BrainERP.Brain.user.model.UserOrCompany;

public record AuthAccount(
        Long id,
        String email,
        String password,
        UserOrCompany type
) {
}
