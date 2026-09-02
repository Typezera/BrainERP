package BrainERP.Brain.company.controller.company;


import BrainERP.Brain.company.dto.CompanyRequestDto;
import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.query.FindByIdCompanyQuery;
import BrainERP.Brain.company.query.FindEmailCompanyQuery;
import BrainERP.Brain.company.query.ListCompanysQuery;
import BrainERP.Brain.company.usecase.CreateCompanyUseCase;
import BrainERP.Brain.company.usecase.DeleteCompanyUseCase;
import BrainERP.Brain.company.usecase.UpdateActivateCompanyUseCase;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/company")
public class CompanyController {
    private final CreateCompanyUseCase createCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;
    private final UpdateActivateCompanyUseCase updateActivateCompanyUseCase;
    private final FindByIdCompanyQuery findByIdCompanyQuery;
    private final FindEmailCompanyQuery findEmailCompanyQuery;
    private final ListCompanysQuery listCompanysQuery;

    public CompanyController(
            CreateCompanyUseCase createCompanyUseCase,
            DeleteCompanyUseCase deleteCompanyUseCase,
            UpdateActivateCompanyUseCase updateActivateCompanyUseCase,
            FindByIdCompanyQuery findByIdCompanyQuery,
            FindEmailCompanyQuery findEmailCompanyQuery,
            ListCompanysQuery listCompanysQuery
    )
    {
        this.createCompanyUseCase = createCompanyUseCase;
        this.deleteCompanyUseCase = deleteCompanyUseCase;
        this.updateActivateCompanyUseCase = updateActivateCompanyUseCase;
        this.findByIdCompanyQuery = findByIdCompanyQuery;
        this.findEmailCompanyQuery = findEmailCompanyQuery;
        this.listCompanysQuery = listCompanysQuery;
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

    @GetMapping("/getId/{id}")
    public ResponseEntity<CompanyResponseDto> getById(
            @PathVariable Long id
    ){
        var comp = findByIdCompanyQuery.findById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comp);
    }

    @GetMapping("/getEmail/{email}")
    public ResponseEntity<CompanyResponseDto> getByEmail(
            @PathVariable String email
    ){
        var comp = findEmailCompanyQuery.findByEmail(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CompanyResponseDto>> getAll(){
        return ResponseEntity.ok(listCompanysQuery.getAllCompanys());
    }
}
