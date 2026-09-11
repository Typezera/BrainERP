package BrainERP.Brain.Order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequestDto(
        @NotNull
        Long productID,

        @NotNull
        @Positive
        Integer quantity
) {
}
