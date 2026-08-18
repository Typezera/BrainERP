package BrainERP.Brain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDto(

    @NotBlank(message = "Campo obrigatório")
    String name,

    @NotBlank(message = "Campo obrigatório")
    String description,

    @NotNull
    Integer stockQuantity,

    @NotNull
    BigDecimal price

) {
}






