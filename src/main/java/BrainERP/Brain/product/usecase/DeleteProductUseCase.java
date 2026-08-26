package BrainERP.Brain.product.usecase;

import BrainERP.Brain.company.service.CompanySecurityService;
import BrainERP.Brain.product.dto.ProductResponseDto;
import BrainERP.Brain.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteProductUseCase {
    final private ProductRepository productRepository;
    final private CompanySecurityService companySecurityService;

    public DeleteProductUseCase (ProductRepository productRepository, CompanySecurityService companySecurityService)
    {
        this.productRepository = productRepository;
        this.companySecurityService = companySecurityService;
    }

    public ProductResponseDto deleteProduct(Long id){
        var prod = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não cadastrado."
                ));

        var comp = companySecurityService.getLoggedCompany();

        if (!prod.getCompany().getId().equals(comp.getId())){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para REMOVER este produto."
            );
        }

        productRepository.deleteById(prod.getId());

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
