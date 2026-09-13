package BrainERP.Brain.Order.usecase;

import BrainERP.Brain.Order.dto.request.OrderItemRequestDto;
import BrainERP.Brain.Order.dto.request.OrderRequestDto;
import BrainERP.Brain.Order.dto.response.OrderItemResponseDto;
import BrainERP.Brain.Order.dto.response.OrderResponseDto;
import BrainERP.Brain.Order.model.OrderItemModel;
import BrainERP.Brain.Order.model.OrderModel;
import BrainERP.Brain.Order.orderstatus.OrderStatus;
import BrainERP.Brain.Order.repository.OrderRepository;
import BrainERP.Brain.product.model.ProductModel;
import BrainERP.Brain.product.repository.ProductRepository;
import BrainERP.Brain.user.model.UserModel;
import BrainERP.Brain.user.service.UserSecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserSecurityService userSecurityService;

    public CreateOrderUseCase(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserSecurityService userSecurityService
    ){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userSecurityService = userSecurityService;
    }

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto request){
        UserModel user = userSecurityService.getLoggedUser();
        OrderModel order = new OrderModel();

        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setTotal(BigDecimal.ZERO);

        for (OrderItemRequestDto itemRequest : request.items()) {
            ProductModel product = productRepository.findByIdAndActivateTrue(itemRequest.productId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Produto não encontrada Ou indisponível"
                    ));

            OrderItemModel orderItem = new OrderItemModel();

            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setUnitPrice(product.getPrice());

            order.addItem(orderItem);

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.quantity()));

            order.setTotal(
                    order.getTotal().add(subtotal)
            );
        }

        OrderModel savedOrder = orderRepository.save(order);

        return toResponseDto(savedOrder);
    }

    private OrderResponseDto toResponseDto(OrderModel savedOrder) {
        List<OrderItemResponseDto> items = savedOrder.getItems()
                .stream()
                .map(item -> new OrderItemResponseDto(
                        item.getProduct().getId(),
                        item.getQuantity(),
                        item.getUnitPrice()
                ))
                .toList();

        return new OrderResponseDto(
                savedOrder.getId(),
                savedOrder.getTotal(),
                savedOrder.getStatus(),
                savedOrder.getCreatedAt(),
                items
        );
    }
}
