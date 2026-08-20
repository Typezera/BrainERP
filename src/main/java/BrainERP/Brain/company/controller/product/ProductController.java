package BrainERP.Brain.company.controller.product;

import BrainERP.Brain.product.dto.ProductRequestDto;
import BrainERP.Brain.product.dto.ProductResponseDto;
import BrainERP.Brain.product.usecase.CreateProductUseCase;
import BrainERP.Brain.product.usecase.UpdateProductUseCase;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/company/product")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController
            (
                CreateProductUseCase createProductUseCase,
                UpdateProductUseCase updateProductUseCase
            )
    {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;

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

    @PatchMapping("/updated/{id}")
    public ResponseEntity<ProductResponseDto>updatedProduct(
            @Valid
            @PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto
    )
    {
        var prod = updateProductUseCase.updateProduct(id,productRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }
}
