package BrainERP.Brain.Order.usecase;

import BrainERP.Brain.Order.dto.request.OrderRequestDto;
import BrainERP.Brain.Order.dto.request.OrderUpdateDto;
import BrainERP.Brain.Order.dto.response.OrderItemResponseDto;
import BrainERP.Brain.Order.dto.response.OrderResponseDto;
import BrainERP.Brain.Order.repository.OrderRepository;
import BrainERP.Brain.company.service.CompanySecurityService;
import BrainERP.Brain.product.usecase.UpdateProductUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateOrderUseCase {
    private final OrderRepository orderRepository;
    private final CompanySecurityService companySecurityService;

    public UpdateOrderUseCase(
            OrderRepository orderRepository,
            CompanySecurityService companySecurityService
    ){
        this.orderRepository = orderRepository;
        this.companySecurityService = companySecurityService;
    }

    public OrderResponseDto updateOrderStatus(Long id, OrderUpdateDto request){
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido não encontrado"
                ));

        companySecurityService.getLoggedCompany();

        order.setStatus(request.status());

        var orderUpdated = orderRepository.save(order);

        return new OrderResponseDto(
                orderUpdated.getId(),
                orderUpdated.getTotal(),
                orderUpdated.getStatus(),
                orderUpdated.getCreatedAt(),
                orderUpdated.getItems().stream()
                        .map(item -> new OrderItemResponseDto(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getUnitPrice()
                        ))
                        .toList()
        );
    }
}
