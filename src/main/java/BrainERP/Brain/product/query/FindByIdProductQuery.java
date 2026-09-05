package BrainERP.Brain.product.query;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.model.CompanyModel;
import BrainERP.Brain.product.dto.ProductResponseCompleteDto;
import BrainERP.Brain.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindByIdProductQuery {
    final private ProductRepository productRepository;

    public FindByIdProductQuery(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseCompleteDto findProductById(Long id){
        var prod = productRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado."
                ));

        CompanyResponseDto comp = new CompanyResponseDto(
                prod.getCompany().getId(),
                prod.getCompany().getName(),
                prod.getCompany().getEmail(),
                prod.getCompany().getHowAreYou(),
                prod.getCompany().getCreatedAt()
        );

        return new ProductResponseCompleteDto(
                prod.getId(),
                prod.getName(),
                prod.getDescription(),
                prod.getPrice(),
                prod.getStockQuantity(),
                prod.getCreatedAt(),
                comp
        );
    }
}
