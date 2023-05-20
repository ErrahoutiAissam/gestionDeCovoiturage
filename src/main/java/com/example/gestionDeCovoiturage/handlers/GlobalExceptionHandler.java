package com.example.gestionDeCovoiturage.handlers;

import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.EmailAlreadyUsedException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(value = NotFoundException.class)
   public ResponseEntity<?> notFoundException(NotFoundException exception) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
   }


   @ExceptionHandler(value = AlreadyExistException.class)
   public ResponseEntity<?> alreadyExistException(EmailAlreadyUsedException exception) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
   }
}