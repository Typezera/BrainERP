package BrainERP.Brain.product.dto;

import BrainERP.Brain.company.dto.CompanyResponseDto;
import BrainERP.Brain.company.model.CompanyModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseCompleteDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuanitty,
        LocalDateTime createdAt,
        CompanyResponseDto company
) {
}
