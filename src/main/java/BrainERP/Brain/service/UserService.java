package BrainERP.Brain.service;

import BrainERP.Brain.dtos.userDtos.UserPatchDto;
import BrainERP.Brain.dtos.userDtos.UserRequestDto;
import BrainERP.Brain.dtos.userDtos.UserResponseDto;
import BrainERP.Brain.model.UserModel;
import BrainERP.Brain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDto CreateAccount(UserRequestDto userRequestDto){
        userRepository.findByEmail(userRequestDto.email())
                .ifPresent(user -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Esse email já foi cadastrado."
                    );
                });

        UserModel userModel = new UserModel();
        userModel.setName(userRequestDto.name());
        userModel.setEmail(userRequestDto.email());
        userModel.setPassword(userRequestDto.password());
        userModel.setHowAreYou(userRequestDto.howAreYou());
        userModel.setActivate(true);

        UserModel user = userRepository.save(userModel);

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getHowAreYou()
        );
    }

    public List<UserResponseDto>searchUsers(){
        var users = userRepository.findAllByActivateTrue();

        return users.stream().map(user -> new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getHowAreYou()
        ))
                .toList();
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

    @Transactional
    public void deactivateUser(Long id){
        var user = userRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario não existe"
                ));

        user.setActivate(false);
    }

}
