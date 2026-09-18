package BrainERP.Brain.exception;

public record ErrorResponseDto(
        int status,
        String message
) {
}
