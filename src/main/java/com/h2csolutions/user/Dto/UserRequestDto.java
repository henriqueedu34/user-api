package com.h2csolutions.user.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "O nome de usuário é obrigatório")
    private String username;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    @NotBlank(message = "O telefone é obrigatório")
    private String phoneNumber;

    @NotBlank(message = "O endereço é obrigatório")
    private String address;

    private String confirmationToken;

}
