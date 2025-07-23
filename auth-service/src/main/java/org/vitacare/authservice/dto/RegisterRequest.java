package org.vitacare.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "Email não pode ser vazio")
    @Email(message = "Email deve ser valido")
    private String email;

    @NotEmpty(message = "Senha não deve ser vazio")
    private String password;

    @NotBlank(message = "O perfiL do usuário é obrigatório")
    private String roleName;
}