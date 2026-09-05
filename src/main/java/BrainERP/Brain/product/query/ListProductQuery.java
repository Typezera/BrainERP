package BrainERP.Brain.product.query;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.product.dto.ProductResponseCompleteDto;
import BrainERP.Brain.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProductQuery {
    final private ProductRepository productRepository;

    public ListProductQuery(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductResponseCompleteDto>findAllProducts(){
           var products = productRepository.findAllByActivateTrue();

           return products.stream()
                   .map(product -> {
                       var company = new CompanyResponseDto(
                               product.getCompany().getId(),
                               product.getCompany().getName(),
                               product.getCompany().getEmail(),
                               product.getCompany().getHowAreYou(),
                               product.getCompany().getCreatedAt()
                       );

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
