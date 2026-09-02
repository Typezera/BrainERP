package BrainERP.Brain.company.query;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindByIdCompanyQuery {
    private final CompanyRepository companyRepository;

    public FindByIdCompanyQuery(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public CompanyResponseDto findById(Long id){
        var comp = companyRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empresa não encontrada"
                ));

        return new CompanyResponseDto(
                comp.getId(),
                comp.getName(),
                comp.getEmail(),
                comp.getHowAreYou(),
                comp.getCreatedAt()
        );
    }
}
