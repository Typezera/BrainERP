package BrainERP.Brain.company.controller.product;

import BrainERP.Brain.product.dto.ProductRequestDto;
import BrainERP.Brain.product.dto.ProductResponseCompleteDto;
import BrainERP.Brain.product.dto.ProductResponseDto;
import BrainERP.Brain.product.query.FindByIdProductQuery;
import BrainERP.Brain.product.query.ListProductQuery;
import BrainERP.Brain.product.usecase.CreateProductUseCase;
import BrainERP.Brain.product.usecase.DeleteProductUseCase;
import BrainERP.Brain.product.usecase.UpdateProductUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/company/product")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final FindByIdProductQuery findByIdProductQuery;
    private final ListProductQuery listProductQuery;

    public ProductController
            (
                CreateProductUseCase createProductUseCase,
                UpdateProductUseCase updateProductUseCase,
                DeleteProductUseCase deleteProductUseCase,
                FindByIdProductQuery findByIdProductQuery,
                ListProductQuery listProductQuery
            )
    {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.findByIdProductQuery = findByIdProductQuery;
        this.listProductQuery = listProductQuery;

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

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductResponseCompleteDto> findProductById(
            @PathVariable Long id
    ){
        var prod = findByIdProductQuery.findProductById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<ProductResponseCompleteDto>> findAllProducts(){
        return ResponseEntity.ok(listProductQuery.findAllProducts());
    }
}
