package BrainERP.Brain.auth.controller;

import BrainERP.Brain.auth.dto.LoginRequestDTO;
import BrainERP.Brain.auth.dto.LoginResponseDTO;
import BrainERP.Brain.auth.usecase.SignInUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final SignInUseCase signInUseCase;

    public AuthController(SignInUseCase signInUseCase) {
        this.signInUseCase = signInUseCase;
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDTO> signIn(
            @RequestBody LoginRequestDTO requestDTO
    ) {

        LoginResponseDTO response =
                signInUseCase.execute(requestDTO);

        return ResponseEntity.ok(response);
    }
}
