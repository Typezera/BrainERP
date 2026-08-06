package BrainERP.Brain.auth.usecase;


import BrainERP.Brain.auth.AuthInterface.AuthPrincipal;
import BrainERP.Brain.auth.dto.LoginRequestDTO;
import BrainERP.Brain.auth.dto.LoginResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SignInUseCase {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public SignInUseCase(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder){
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    public LoginResponseDTO execute(LoginRequestDTO requestDTO){

        System.out.println("Entrou no SignInUseCase");
        var  authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.email(),
                        requestDTO.password()
                )
        );

        var now = Instant.now();
        var principal = (AuthPrincipal) authentication.getPrincipal();

        var claims = JwtClaimsSet.builder()
                .subject(principal.getEmail())
                .claim("id", principal.getId())
                .claim("accountType", principal.getHowAreYou().name())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .build();

        var header = JwsHeader.with(() -> "HS256").build();

        var token = jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims)
        );

        return new LoginResponseDTO(
                token.getTokenValue()
        );
    }
}


