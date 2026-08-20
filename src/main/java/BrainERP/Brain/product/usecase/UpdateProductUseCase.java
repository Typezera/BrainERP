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
public class UpdateProductUseCase {
    private final ProductRepository productRepository;
    private final CompanySecurityService companySecurityService;

    public UpdateProductUseCase(ProductRepository productRepository, CompanySecurityService companySecurityService){
        this.productRepository = productRepository;
        this.companySecurityService = companySecurityService;
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto prodRequest){
        var prod =  productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        var comp = companySecurityService.getLoggedCompany();

        prod.setName(prodRequest.name());
        prod.setDescription(prodRequest.description());
        prod.setStockQuantity(prodRequest.stockQuantity());
        prod.setPrice(prodRequest.price());

        var updatedProd = productRepository.save(prod);

        return new ProductResponseDto(
                updatedProd.getId(),
                updatedProd.getName(),
                updatedProd.getDescription(),
                updatedProd.getPrice(),
                updatedProd.getStockQuantity(),
                updatedProd.getCreatedAt()
        );

    }

}
