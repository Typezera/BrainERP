package BrainERP.Brain.product.query;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.model.CompanyModel;
import BrainERP.Brain.product.dto.ProductResponseCompleteDto;
import BrainERP.Brain.product.mapper.CompanyMapper;
import BrainERP.Brain.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProductQuery {
    final private ProductRepository productRepository;
    final private CompanyMapper companyMapper;

    public ListProductQuery(ProductRepository productRepository, CompanyMapper companyMapper){
        this.productRepository = productRepository;
        this.companyMapper = companyMapper;
    }

    public List<ProductResponseCompleteDto>findAllProducts(){
           var products = productRepository.findAllByActivateTrue();

           return products.stream()
                   .map(product -> {
                       var company = companyMapper.toCompanyResponseDto(product.getCompany());

                       return new ProductResponseCompleteDto(
                               product.getId(),
                               product.getName(),
                               product.getDescription(),
                               product.getPrice(),
                               product.getStockQuantity(),
                               product.getCreatedAt(),
                               company
                       );
                   })
                   .toList();
    }



}
