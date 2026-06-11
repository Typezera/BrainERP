package BrainERP.Brain.user.query;

import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.repository.UserRepository;
import org.hibernate.annotations.processing.Find;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindByEmailUserQuery {

    private final UserRepository userRepository;

    public FindByEmailUserQuery(UserRepository userRepository){this.userRepository = userRepository;}

    public UserResponseDto findUserByEmail(String email){
        var user = userRepository.findByEmailAndActivateTrue(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado"
                ));

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getHowAreYou()
        );

    }
}
