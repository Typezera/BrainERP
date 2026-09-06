package BrainERP.Brain.company.usecase;


import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.repository.CompanyRepository;
import BrainERP.Brain.company.service.CompanySecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateActivateCompanyUseCase {
    private final CompanyRepository companyRepository;
    private final CompanySecurityService companySecurityService;

    public UpdateActivateCompanyUseCase(CompanyRepository companyRepository, CompanySecurityService companySecurityService){
        this.companyRepository = companyRepository;
        this.companySecurityService = companySecurityService;
    }

    @Transactional
    public CompanyResponseDto reactivateAccountCompany(String email){
        var comp = companyRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Este email não foi encontrado."
                ));

        companySecurityService.checkRealCompany(comp);

        comp.setActivate(true);

        return new CompanyResponseDto(
                comp.getId(),
                comp.getName(),
                comp.getEmail(),
                comp.getHowAreYou(),
                comp.getCreatedAt()
        );
    }
}
