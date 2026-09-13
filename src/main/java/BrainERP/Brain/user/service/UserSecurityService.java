package BrainERP.Brain.user.service;

import BrainERP.Brain.user.model.UserModel;
import BrainERP.Brain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserSecurityService {
    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserModel getLoggedUser(){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        Jwt jwt =
                (Jwt) authentication.getPrincipal();

        String accountType = jwt.getClaim("accountType");

        if (!"USER".equals(accountType)){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Apenas um usuário pode fazer essa operação"
            );
        }

        Long userId = jwt.getClaim("id");

        return userRepository.findByIdAndActivateTrue(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

    }

    public void checkRealUser(UserModel user){
        var userLogged = getLoggedUser();

        if (!user.getId().equals(userLogged.getId())){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para alterar esse usuário."
            );
        }
    }
}
