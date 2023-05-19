package com.example.gestionDeCovoiturage.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {

    private String nom;
    private String prenom;
    private String email;
    private String password;
}
