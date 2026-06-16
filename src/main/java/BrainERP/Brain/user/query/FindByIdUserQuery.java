package BrainERP.Brain.user.query;

import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindByIdUserQuery {
    private final UserRepository userRepository;

    public FindByIdUserQuery(UserRepository userRepository){this.userRepository = userRepository;}

    public UserResponseDto findUserByIdQuery(Long id){
        var user = userRepository.findByIdAndActivateTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado"
                ));

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

}

