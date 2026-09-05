package BrainERP.Brain.user.usecase;

import BrainERP.Brain.user.repository.UserRepository;
import BrainERP.Brain.user.service.UserSecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteUserUseCase {
    private final UserRepository userRepository;
    private final UserSecurityService userSecurityService;

    public DeleteUserUseCase(UserRepository userRepository, UserSecurityService userSecurityService){
        this.userRepository = userRepository;
        this.userSecurityService = userSecurityService;
    }

    @Transactional
    public void deactivateUser(Long id){
        var user = userRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario não existe"
                ));

        userSecurityService.checkRealUser(user);

        user.setActivate(false);
    }
}
