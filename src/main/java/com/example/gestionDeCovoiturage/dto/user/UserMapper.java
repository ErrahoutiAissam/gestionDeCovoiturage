package com.example.gestionDeCovoiturage.dto.user;

import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

   @Mapping(target = "imageUrl", ignore = true)
   UtilisateurDTO utilisateurToUtilisateurDTO(Utilisateur utilisateur);

   @Mapping(target = "image", ignore = true)
   @Mapping(target = "role", ignore = true)
   Utilisateur utilisateurDTOToUtilisateur(UtilisateurDTO utilisateurDTO);

   @Mapping(target = "image", ignore = true)
   @Mapping(target = "role", ignore = true)
   Utilisateur registerRequestToUtilisateur(RegisterRequest registerRequest);


   @Mapping(target = "imageUrl", source = "image.url")
   @Mapping(target = "password", ignore = true)
   @Mapping(target = "role", source = "role.authority")
   UtilisateurDTO toUtilisateurResponseDTO(Utilisateur utilisateur);



}
