package BrainERP.Brain.UserDTOs;

import BrainERP.Brain.model.UserOrCompany;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        UserOrCompany howAreYou
) {
}
