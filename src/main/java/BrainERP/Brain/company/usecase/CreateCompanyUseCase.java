package BrainERP.Brain.company.usecase;

import BrainERP.Brain.company.dto.CompanyRequestDto;
import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.model.CompanyModel;
import BrainERP.Brain.company.repository.CompanyRepository;
import BrainERP.Brain.user.model.UserOrCompany;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateCompanyUseCase {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder){
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public CompanyResponseDto creatAccount(CompanyRequestDto companyRequestDto){
        companyRepository.findByEmail(companyRequestDto.email())
                .ifPresent(company -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Essa Em,ail já foi cadastrado"
                    );
                });

        CompanyModel companyModel = new CompanyModel();
        companyModel.setName(companyRequestDto.name());
        companyModel.setEmail(companyRequestDto.email());
        companyModel.setCnpj(companyRequestDto.cnpj());
        companyModel.setPassword(passwordEncoder.encode(companyRequestDto.password()));
        companyModel.setHowAreYou(UserOrCompany.COMPANY);
        companyModel.setActivate(true);

        CompanyModel comp = companyRepository.save(companyModel);

        return new CompanyResponseDto(
                comp.getId(),
                comp.getName(),
                comp.getEmail(),
                comp.getHowAreYou(),
                comp.getCreatedAt()
        );
    }
}
