package BrainERP.Brain.Order.controller;

import BrainERP.Brain.Order.dto.request.OrderRequestDto;
import BrainERP.Brain.Order.dto.response.OrderResponseDto;
import BrainERP.Brain.Order.query.FindOrderById;
import BrainERP.Brain.Order.repository.OrderRepository;
import BrainERP.Brain.Order.usecase.CreateOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/order")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrderById findOrderById;

    public OrderController(
            CreateOrderUseCase createOrderUseCase,
            FindOrderById findOrderById
    ){
        this.createOrderUseCase = createOrderUseCase;
        this.findOrderById = findOrderById;
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

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(
            @PathVariable
            Long id
    ){
        var order = findOrderById.findOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
