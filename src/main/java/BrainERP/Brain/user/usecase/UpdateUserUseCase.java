package BrainERP.Brain.user.usecase;

import BrainERP.Brain.user.dto.UserPatchDto;
import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDto userPatch(Long id, UserPatchDto userPatchDto){
        var user = userRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado"
                ));

        if (userPatchDto.name() != null && !userPatchDto.name().isBlank()){user.setName(userPatchDto.name());}
        if (userPatchDto.email() != null && !userPatchDto.email().isBlank()){user.setEmail(userPatchDto.email());}
        if (userPatchDto.password() != null && !userPatchDto.password().isBlank()){user.setPassword(userPatchDto.password());}

        var updatedUser = userRepository.save(user);

        return new UserResponseDto(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getCreatedAt(),
                updatedUser.getHowAreYou()
        );
    }
}
