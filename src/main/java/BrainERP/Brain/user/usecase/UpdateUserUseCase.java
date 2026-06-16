package BrainERP.Brain.user.usecase;

import BrainERP.Brain.user.dto.UserPatchDto;
import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.model.UserModel;
import BrainERP.Brain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto userPatch(Long id, UserPatchDto userPatchDto){
        var user = userRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado"
                ));

        return appyPatch(user, userPatchDto);
    }

    public UserResponseDto appyPatch(UserModel user, UserPatchDto userPatch){

        boolean updated = false;

        if (userPatch.name() != null && !userPatch.name().isBlank()) {
            user.setName(userPatch.name());
            updated = true;
        }

        if (userPatch.email() != null && !userPatch.email().isBlank()) {
            user.setEmail(userPatch.email());
            updated = true;
        }

        if (userPatch.password() != null && !userPatch.password().isBlank()) {
            user.setPassword(
                    passwordEncoder.encode(userPatch.password())
            );
            updated = true;
        }

        if (!updated){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Nenhum valor valido para atualizar"
            );
        }

        var updatedUser = userRepository.save(user);

        return new UserResponseDto(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getCreatedAt()
        );
    }


}
