package BrainERP.Brain.company.controller.product;

import BrainERP.Brain.product.dto.ProductRequestDto;
import BrainERP.Brain.product.dto.ProductResponseDto;
import BrainERP.Brain.product.usecase.CreateProductUseCase;
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
@RequestMapping("api/company/product")
public class CreateProductController {
    private final CreateProductUseCase createProductUseCase;

    public CreateProductController(CreateProductUseCase createProductUseCase){
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto>creatProduct(
            @Valid
            @RequestBody
            ProductRequestDto productRequestDto
    )
    {
        var prod = createProductUseCase.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }
}
