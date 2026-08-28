package BrainERP.Brain.company.usecase;


import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.repository.CompanyRepository;
import BrainERP.Brain.company.service.CompanySecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteCompanyUseCase {
    private final CompanyRepository companyRepository;
    private final CompanySecurityService companySecurityService;


    public DeleteCompanyUseCase(
            CompanyRepository companyRepository,
            CompanySecurityService companySecurityService
    ){
        this.companyRepository = companyRepository;
        this.companySecurityService = companySecurityService;
    }

    @Transactional
    public CompanyResponseDto deactivateCompany(Long id){
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Compania não encontrada."
                ));

        var comp = companySecurityService.getLoggedCompany();

        if (!company.getId().equals(comp.getId())){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para DESATIVAR está conta."
            );
        }

        company.setActivate(false);

        return new CompanyResponseDto(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getHowAreYou(),
                company.getCreatedAt()
        );
    }
}
