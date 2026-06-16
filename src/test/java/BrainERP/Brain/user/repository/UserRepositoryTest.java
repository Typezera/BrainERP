package BrainERP.Brain.user.repository;


import BrainERP.Brain.user.dto.UserRequestDto;
import BrainERP.Brain.user.model.UserModel;
import jakarta.persistence.EntityManager;
import org.h2.engine.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Return successfully from db")
    void findByEmailCase1() {
        UserRequestDto data = new UserRequestDto("Willian", "willianteste@teste.com", "12345");
        this.createUser(data);

        Optional<UserModel> foundedEmail = this.userRepository.findByEmail(data.email());

        assertThat(foundedEmail.isPresent()).isTrue();
    }

    @Test
    void findByEmailAndActivateTrue() {
    }

    @Test
    void findAllByActivateTrue() {
    }

    @Test
    void findByIdAndActivateTrue() {
    }

    private UserModel createUser(UserRequestDto user){
        UserModel newUser = new UserModel(user);
        this.entityManager.persist(newUser);
        return newUser;
    }
}