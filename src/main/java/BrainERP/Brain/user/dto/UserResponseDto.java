package BrainERP.Brain.user.dto;

import BrainERP.Brain.user.model.UserOrCompany;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt
) {
}
