package BrainERP.Brain.company.controller.company;


import BrainERP.Brain.company.dto.CompanyRequestDto;
import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.usecase.CreateCompanyUseCase;
import BrainERP.Brain.company.usecase.DeleteCompanyUseCase;
import BrainERP.Brain.company.usecase.UpdateActivateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/company")
public class CompanyController {
    private final CreateCompanyUseCase createCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;
    private final UpdateActivateCompanyUseCase updateActivateCompanyUseCase;

    public CompanyController(
            CreateCompanyUseCase createCompanyUseCase,
            DeleteCompanyUseCase deleteCompanyUseCase,
            UpdateActivateCompanyUseCase updateActivateCompanyUseCase
    )
    {
        this.createCompanyUseCase = createCompanyUseCase;
        this.deleteCompanyUseCase = deleteCompanyUseCase;
        this.updateActivateCompanyUseCase = updateActivateCompanyUseCase;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CompanyResponseDto> deleteCompany(
            @PathVariable Long id
    ){
        var comp = deleteCompanyUseCase.deactivateCompany(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comp);
    }

    @PatchMapping("/reactivate/{email}")
    public ResponseEntity<CompanyResponseDto> reactivateCompany(
            @PathVariable String email
    ){
        var comp = updateActivateCompanyUseCase.reactivateAccountCompany(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comp);
    }
}
