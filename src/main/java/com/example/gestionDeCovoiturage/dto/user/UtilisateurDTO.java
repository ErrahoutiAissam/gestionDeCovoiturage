package com.example.gestionDeCovoiturage.dto.user;

import com.example.gestionDeCovoiturage.models.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtilisateurDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String imageUrl;
    private String role;

}
