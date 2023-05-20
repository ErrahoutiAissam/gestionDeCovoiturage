package com.example.gestionDeCovoiturage.exceptions.notfound;

import com.example.gestionDeCovoiturage.models.Utilisateur;

public class UserNotFoundException extends NotFoundException{
   public UserNotFoundException() {
      this("Utilisateur introuvable");
   }

   public UserNotFoundException(String message) {
      super(Utilisateur.class, message);
   }

}
