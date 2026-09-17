package BrainERP.Brain.Order.dto.request;

import BrainERP.Brain.Order.orderstatus.OrderStatus;

public record OrderUpdateDto(
        OrderStatus status
) {
}
