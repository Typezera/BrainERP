package BrainERP.Brain.product.query;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.model.CompanyModel;
import BrainERP.Brain.product.dto.ProductResponseCompleteDto;
import BrainERP.Brain.product.mapper.CompanyMapper;
import BrainERP.Brain.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindByIdProductQuery {
    final private ProductRepository productRepository;
    final private CompanyMapper companyMapper;

    public FindByIdProductQuery(ProductRepository productRepository, CompanyMapper companyMapper){
        this.productRepository = productRepository;
        this.companyMapper = companyMapper;
    }

    public ProductResponseCompleteDto findProductById(Long id){
        var prod = productRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado."
                ));

        var company = companyMapper.toCompanyResponseDto(prod.getCompany());

        return new ProductResponseCompleteDto(
                prod.getId(),
                prod.getName(),
                prod.getDescription(),
                prod.getPrice(),
                prod.getStockQuantity(),
                prod.getCreatedAt(),
                company
        );
    }
}
