package BrainERP.Brain.Order.query;

import BrainERP.Brain.Order.dto.response.OrderItemResponseDto;
import BrainERP.Brain.Order.dto.response.OrderResponseDto;
import BrainERP.Brain.Order.model.OrderModel;
import BrainERP.Brain.Order.repository.OrderRepository;
import BrainERP.Brain.user.model.UserModel;
import BrainERP.Brain.user.repository.UserRepository;
import BrainERP.Brain.user.service.UserSecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindOrderById {
    private final OrderRepository orderRepository;
    private final UserSecurityService userSecurityService;

    public FindOrderById(
            OrderRepository orderRepository,
            UserSecurityService userSecurityService,
            UserRepository userRepository
    )
    {
        this.orderRepository = orderRepository;
        this.userSecurityService = userSecurityService;
    }

    public OrderResponseDto findOrderById(Long id){
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido não encontrado"
                ));

        verifyUser(order);

        return new OrderResponseDto(
                order.getId(),
                order.getTotal(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getItems().stream()
                        .map(item ->  new OrderItemResponseDto(
                            item.getProduct().getId(),
                            item.getProduct().getName(),
                            item.getQuantity(),
                            item.getUnitPrice()
                        ))
                        .toList()
        );
    }

    public void verifyUser(OrderModel order){
        userSecurityService.checkRealUser(order.getUser());
    }
}
