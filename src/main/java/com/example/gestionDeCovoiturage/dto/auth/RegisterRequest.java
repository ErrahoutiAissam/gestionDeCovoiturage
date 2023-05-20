package com.example.gestionDeCovoiturage.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Size(min = 3, max = 50, message = "La taille du nom doit être comprise entre {min} & {max}")
    private String nom;

    @NotBlank
    @Size(min = 3, max = 50, message = "La taille du prenom doit être comprise entre {min} & {max}")
    private String prenom;

    @NotBlank
    @Email(message = "Email invalid")
    private String email;


    @NotBlank
    @Size(min = 8, max = 50, message = "La taille du mot de passe doit être comprise entre {min} & {max}")
    private String password;
}
