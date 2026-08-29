package BrainERP.Brain.company.usecase;


import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateActivateCompanyUseCase {
    private final CompanyRepository companyRepository;

    public UpdateActivateCompanyUseCase(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CompanyResponseDto reactivateAccountCompany(String email){
        var reco = companyRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Este email não foi encontrado."
                ));

        reco.setActivate(true);

        return new CompanyResponseDto(
                reco.getId(),
                reco.getName(),
                reco.getEmail(),
                reco.getHowAreYou(),
                reco.getCreatedAt()
        );
    }
}
