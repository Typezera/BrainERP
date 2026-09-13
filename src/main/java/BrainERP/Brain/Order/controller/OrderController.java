package BrainERP.Brain.Order.controller;

import BrainERP.Brain.Order.dto.request.OrderRequestDto;
import BrainERP.Brain.Order.dto.response.OrderResponseDto;
import BrainERP.Brain.Order.repository.OrderRepository;
import BrainERP.Brain.Order.usecase.CreateOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(OrderRepository orderRepository, CreateOrderUseCase createOrderUseCase){
        this.createOrderUseCase = createOrderUseCase;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/created")
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid
            @RequestBody
            OrderRequestDto orderRequestDto
    )
    {
        var order = createOrderUseCase.createOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
