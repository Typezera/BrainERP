package BrainERP.Brain.product.mapper;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.model.CompanyModel;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyResponseDto toCompanyResponseDto(CompanyModel companyModel){
        return new CompanyResponseDto(
                companyModel.getId(),
                companyModel.getName(),
                companyModel.getEmail(),
                companyModel.getHowAreYou(),
                companyModel.getCreatedAt()
        );
    }
}
