package com.example.gestionDeCovoiturage.exceptions.alreadyExists;

public class AlreadyExistException extends Exception {
   public AlreadyExistException() {
      super();
   }

   public AlreadyExistException(String message) {
      super(message);
   }
}
