package BrainERP.Brain.Order.query;

import BrainERP.Brain.Order.dto.response.OrderItemResponseDto;
import BrainERP.Brain.Order.dto.response.OrderResponseDto;
import BrainERP.Brain.Order.repository.OrderRepository;
import BrainERP.Brain.user.repository.UserRepository;
import BrainERP.Brain.user.service.UserSecurityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllOrderQuery {
    private final OrderRepository orderRepository;
    private final UserSecurityService userSecurityService;

    public GetAllOrderQuery(
            OrderRepository orderRepository,
            UserRepository userRepository,
            UserSecurityService userSecurityService
    ){
        this.orderRepository = orderRepository;
        this.userSecurityService = userSecurityService;
    }

    public List<OrderResponseDto> getAllOrders(){
        var userJwt = userSecurityService.getLoggedUser();
        var orders = orderRepository.findByUserId(userJwt.getId());

        return orders.stream().map(order -> new OrderResponseDto(
                order.getId(),
                order.getTotal(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getItems().stream()
                        .map(item -> new OrderItemResponseDto(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getUnitPrice()
                        ))
                        .toList()

                ))
                .toList();
    }
}
