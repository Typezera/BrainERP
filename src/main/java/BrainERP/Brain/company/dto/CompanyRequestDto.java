package BrainERP.Brain.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyRequestDto(
        @NotBlank(message = "Informe o nome da empresa")
        String nome,
        @NotBlank(message = "Informe o email da empresa")
        @Email(message = "Formato de Email inválido")
        String email,

        @NotBlank(message = "Informe o CNPJ")
        @CNPJ(message = "Formato de cnpj inválido")
        String cnpj,

        @NotBlank(message = "Campo senha obrigatorio")
        String password
) {
}
