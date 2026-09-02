package BrainERP.Brain.company.query;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCompanysQuery {
    private final CompanyRepository companyRepository;

    public ListCompanysQuery(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<CompanyResponseDto> getAllCompanys(){
        var companys = companyRepository.findAllByActivateTrue();

        return companys.stream().map(comp -> new CompanyResponseDto(
                comp.getId(),
                comp.getName(),
                comp.getEmail(),
                comp.getHowAreYou(),
                comp.getCreatedAt()
        ))
        .toList();
    }
}
