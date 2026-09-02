package BrainERP.Brain.company.query;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindEmailCompanyQuery {
    final private CompanyRepository companyRepository;

    public FindEmailCompanyQuery(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public CompanyResponseDto findByEmail(String email){
        var comp = companyRepository.findByEmailAndActivateTrue(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empresa não encontrada."
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
