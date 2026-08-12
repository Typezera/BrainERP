package BrainERP.Brain.company.dto;

import BrainERP.Brain.user.model.UserOrCompany;

import java.time.LocalDateTime;

public record CompanyResponseDto(
        Long id,
        String name,
        String email,
        UserOrCompany howAreYou,
        LocalDateTime createdAt
) {
}
