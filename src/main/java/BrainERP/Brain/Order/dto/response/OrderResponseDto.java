package BrainERP.Brain.Order.dto.response;

import BrainERP.Brain.Order.dto.request.OrderItemRequestDto;
import BrainERP.Brain.Order.orderstatus.OrderStatus;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long id,
        BigDecimal total,
        OrderStatus status,
        LocalDateTime createdAt,
        List<OrderItemResponseDto> items
) {
}
