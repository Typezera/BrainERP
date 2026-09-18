package BrainERP.Brain.Order.controller;

import BrainERP.Brain.Order.dto.request.OrderRequestDto;
import BrainERP.Brain.Order.dto.request.OrderUpdateDto;
import BrainERP.Brain.Order.dto.response.OrderResponseDto;
import BrainERP.Brain.Order.query.FindOrderByIdQuery;
import BrainERP.Brain.Order.query.GetAllOrderQuery;
import BrainERP.Brain.Order.usecase.CreateOrderUseCase;
import BrainERP.Brain.Order.usecase.UpdateOrderUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/order")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrderByIdQuery findOrderByIdQuery;
    private final GetAllOrderQuery getAllOrderQuery;
    private final UpdateOrderUseCase updateOrderUseCase;

    public OrderController(
            CreateOrderUseCase createOrderUseCase,
            FindOrderByIdQuery findOrderByIdQuery,
            GetAllOrderQuery getAllOrderQuery,
            UpdateOrderUseCase updateOrderUseCase
    ){
        this.createOrderUseCase = createOrderUseCase;
        this.findOrderByIdQuery = findOrderByIdQuery;
        this.getAllOrderQuery = getAllOrderQuery;
        this.updateOrderUseCase = updateOrderUseCase;
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
        var order = findOrderByIdQuery.findOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
        return ResponseEntity.ok(getAllOrderQuery.getAllOrders());
    }

    @PatchMapping("/company/status/update/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable
            Long id,
            @RequestBody
            OrderUpdateDto orderUpdateDto
    ){
        var orderUpda = updateOrderUseCase.updateOrderStatus(id, orderUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(orderUpda);
    }
}
