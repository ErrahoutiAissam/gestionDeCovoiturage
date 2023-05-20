package com.example.gestionDeCovoiturage.utils;

import java.util.UUID;

public final class NameGenerator {

   public static String generateUniqueFileName() {
      return UUID.randomUUID().toString();
   }

}
