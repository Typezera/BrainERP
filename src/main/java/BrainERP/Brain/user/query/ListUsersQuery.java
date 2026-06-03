package BrainERP.Brain.user.query;

import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUsersQuery {
    private final UserRepository userRepository;

    public ListUsersQuery(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> searchUsers(){
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
