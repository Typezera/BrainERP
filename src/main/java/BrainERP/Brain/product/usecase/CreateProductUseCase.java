package BrainERP.Brain.product.usecase;


import BrainERP.Brain.company.service.CompanySecurityService;
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
    final CompanySecurityService companySecurityService;

    public CreateProductUseCase (ProductRepository productRepository, CompanySecurityService companySecurityService){
        this.productRepository = productRepository;
        this.companySecurityService = companySecurityService;
    }


    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        productRepository.findByName(productRequestDto.name())
                .ifPresent( product -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Esse produto já foi cadastrado."
                    );
                });
        var company = companySecurityService.getLoggedCompany();

        ProductModel product = new ProductModel();
        product.setName(productRequestDto.name());
        product.setDescription(productRequestDto.description());
        product.setStockQuantity(productRequestDto.stockQuantity());
        product.setPrice(productRequestDto.price());
        product.setActivate(true);
        product.setCompany(company);

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
