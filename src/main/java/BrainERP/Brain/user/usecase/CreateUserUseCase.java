package BrainERP.Brain.user.usecase;

import BrainERP.Brain.user.dto.UserRequestDto;
import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.model.UserModel;
import BrainERP.Brain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder){this.userRepository = userRepository; this.passwordEncoder = passwordEncoder;}

    public UserResponseDto createAccount(UserRequestDto userRequestDto){
        userRepository.findByEmail(userRequestDto.email())
                .ifPresent(user -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Esse Email já foi cadastrado."
                    );
                });

        UserModel userModel = new UserModel();
        userModel.setName(userRequestDto.name());
        userModel.setEmail(userRequestDto.email());
        userModel.setPassword(passwordEncoder.encode(userRequestDto.password()));
        userModel.setActivate(true);

        UserModel user = userRepository.save(userModel);

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
