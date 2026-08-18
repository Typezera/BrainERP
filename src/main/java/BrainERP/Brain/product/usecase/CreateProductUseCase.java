package BrainERP.Brain.product.usecase;


import BrainERP.Brain.product.dto.ProductRequestDto;
import BrainERP.Brain.product.dto.ProductResponseDto;
import BrainERP.Brain.product.model.ProductModel;
import BrainERP.Brain.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateProductUseCase {
    final ProductRepository productRepository;

    public CreateProductUseCase (ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        productRepository.findByName(productRequestDto.name())
                .ifPresent( product -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Esse produto já foi cadastrado."
                    );
                });

        ProductModel product = new ProductModel();
        product.setName(productRequestDto.name());
        product.setDescription(productRequestDto.description());
        product.setStockQuantity(productRequestDto.stockQuantity());
        product.setPrice(productRequestDto.price());
        product.setActivate(true);

        ProductModel prod = productRepository.save(product);

        return new ProductResponseDto(
                prod.getId(),
                prod.getName(),
                prod.getDescription(),
                prod.getPrice(),
                prod.getStockQuantity(),
                prod.getCreatedAt()
        );
    }
}
