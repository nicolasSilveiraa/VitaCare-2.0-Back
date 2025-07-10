package org.vitacare.authservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @NotEmpty
    private String token;

    @NotEmpty
    @Size(min = 8, message = "A nova senha deve ter pelo menos 8 caracteres")
    private String newPassword;
}
