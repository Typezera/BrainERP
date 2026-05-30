package BrainERP.Brain.UserDTOs;

import BrainERP.Brain.model.UserOrCompany;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        UserOrCompany howAreYou
) {
}
