package BrainERP.Brain.Order.dto.response;

import java.math.BigDecimal;

public record OrderItemResponseDto(
        Long productId,
        Integer quantity,
        BigDecimal price
) {
}
