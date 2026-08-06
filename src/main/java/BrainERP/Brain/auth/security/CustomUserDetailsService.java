package BrainERP.Brain.auth.security;

import BrainERP.Brain.company.repository.CompanyRepository;
import BrainERP.Brain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public CustomUserDetailsService(UserRepository userRepository, CompanyRepository companyRepository){
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmailAndActivateTrue(email);

        if(user.isPresent()){
            return new SecurityUser(user.get());
        }

        var company = companyRepository.findByEmailAndActivateTrue(email);

        if(company.isPresent()){
            return new SecurityCompany(company.get());
        }

        throw new UsernameNotFoundException("Usuário ou empresa não encontrados");
    }
}
