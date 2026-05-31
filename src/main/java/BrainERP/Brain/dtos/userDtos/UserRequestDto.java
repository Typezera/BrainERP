package BrainERP.Brain.dtos.userDtos;


import BrainERP.Brain.model.UserOrCompany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotBlank(message = "Campo nome Obrigatório")
        String name,

        @NotBlank(message = "Campo email Obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "Campo senha Obrigatório")
        String password,

        @NotNull(message = "Selecione um valor")
        UserOrCompany howAreYou
) {

}
