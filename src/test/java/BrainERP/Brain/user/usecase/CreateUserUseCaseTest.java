package BrainERP.Brain.user.usecase;

import BrainERP.Brain.user.dto.UserRequestDto;
import BrainERP.Brain.user.dto.UserResponseDto;
import BrainERP.Brain.user.model.UserModel;
import BrainERP.Brain.user.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("UserCaseTest")
public class CreateUserUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    @DisplayName("Should create user Successfully")
    void shouldCreateUserSuccessfully(){
        UserRequestDto dto = new UserRequestDto(
                "Willian",
                "willian@teste.com",
                "9876543"
        );

        UserModel user = new UserModel();
        user.setId(1L);
        user.setName(dto.name());
        user.setEmail(dto.email());

        when(userRepository.findByEmail(dto.email()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(dto.password()))
                .thenReturn("EncryptedPassword");

        when(userRepository.save(any(UserModel.class)))
                .thenReturn(user);

        UserResponseDto response =
                createUserUseCase.createAccount(dto);

        assertThat(response).isNotNull();
        assertThat(response.name()).isEqualTo("Willian");
        assertThat(response.email()).isEqualTo("willian@teste.com");

        verify(userRepository).findByEmail(dto.email());
        verify(passwordEncoder).encode(dto.password());
        verify(userRepository).save(any(UserModel.class));
    }

    @Test
    @DisplayName("shows that the email is not empty")
    void shouldEmailIsNotEmpty(){
        UserRequestDto dto = new UserRequestDto(
                "Willian",
                "willian@teste.com",
                "9876543"
        );

        UserModel data = new UserModel();
        data.setId(1L);
        data.setName(dto.name());
        data.setEmail(dto.email());

        when(userRepository.findByEmail(dto.email()))
                .thenReturn(Optional.of(data));

        assertThatThrownBy(() -> createUserUseCase.createAccount(dto))
                .isInstanceOf(ResponseStatusException.class);
    }

}
