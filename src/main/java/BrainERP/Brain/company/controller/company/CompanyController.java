package BrainERP.Brain.company.controller.company;


import BrainERP.Brain.company.dto.CompanyRequestDto;
import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.usecase.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("api/company")
public class CompanyController {
    private final CreateCompanyUseCase createCompanyUseCase;

    public CompanyController(CreateCompanyUseCase createCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<CompanyResponseDto> createCompany(
            @Valid
            @RequestBody
            CompanyRequestDto companyRequestDto)
    {
        var comp = createCompanyUseCase.creatAccount(companyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comp);
    }
}
