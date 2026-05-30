package BrainERP.Brain.service;

import BrainERP.Brain.UserDTOs.UserRequestDto;
import BrainERP.Brain.UserDTOs.UserResponseDto;
import BrainERP.Brain.model.UserModel;
import BrainERP.Brain.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

}
