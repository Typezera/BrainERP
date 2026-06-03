package BrainERP.Brain.user.usecase;

import BrainERP.Brain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository){this.userRepository = userRepository;}

    @Transactional
    public void deactivateUser(Long id){
        var user = userRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario não existe"
                ));
        user.setActivate(false);
    }
}
