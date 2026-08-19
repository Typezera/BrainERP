package BrainERP.Brain.company.service;

import BrainERP.Brain.auth.AuthInterface.AuthPrincipal;
import BrainERP.Brain.company.model.CompanyModel;
import BrainERP.Brain.company.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompanySecurityService {
    private final CompanyRepository companyRepository;

    public CompanySecurityService (CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public CompanyModel getLoggedCompany(){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        Jwt jwt =
                (Jwt) authentication.getPrincipal();

        String accountType = jwt.getClaim("accountType");

        if (!"COMPANY".equals(accountType)){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Apenas empresas podem realizar essa operação"
            );
        }

        Long companyId = jwt.getClaim("id");

        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empresa não encontrada"
                ));
    }
}
