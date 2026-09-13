package BrainERP.Brain.Order.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequestDto(
        @NotEmpty
        List<@Valid OrderItemRequestDto> items
) {
}
