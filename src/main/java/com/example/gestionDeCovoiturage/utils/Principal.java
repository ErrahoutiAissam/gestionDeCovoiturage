package com.example.gestionDeCovoiturage.utils;

import com.example.gestionDeCovoiturage.models.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class Principal {

   public static Utilisateur getCurrentUser() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      return authentication.isAuthenticated()
              ? (Utilisateur) authentication.getPrincipal()
              : null;

   }

}
