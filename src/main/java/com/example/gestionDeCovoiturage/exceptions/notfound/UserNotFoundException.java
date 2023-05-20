package com.example.gestionDeCovoiturage.exceptions.notfound;

public class UserNotFoundException extends Exception{

   public UserNotFoundException() {
   }

   public UserNotFoundException(String message) {
      super(message);
   }
}
