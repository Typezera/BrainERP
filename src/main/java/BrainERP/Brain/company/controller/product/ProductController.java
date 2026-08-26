package BrainERP.Brain.company.controller.product;

import BrainERP.Brain.product.dto.ProductRequestDto;
import BrainERP.Brain.product.dto.ProductResponseDto;
import BrainERP.Brain.product.usecase.CreateProductUseCase;
import BrainERP.Brain.product.usecase.DeleteProductUseCase;
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
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController
            (
                CreateProductUseCase createProductUseCase,
                UpdateProductUseCase updateProductUseCase,
                DeleteProductUseCase deleteProductUseCase
            )
    {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;

    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto>creatProduct(
            @RequestBody
            ProductRequestDto productRequestDto
    )
    {
        var prod = createProductUseCase.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @PatchMapping("/updated/{id}")
    public ResponseEntity<ProductResponseDto>updatedProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto
    )
    {
        var prod = updateProductUseCase.updateProduct(id,productRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponseDto>deleteProduct(
            @PathVariable Long id
    )
    {
        var prod = deleteProductUseCase.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }
}
