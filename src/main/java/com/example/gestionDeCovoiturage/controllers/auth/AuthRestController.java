package com.example.gestionDeCovoiturage.controllers.auth;

import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.EmailAlreadyUsedException;
import com.example.gestionDeCovoiturage.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

   private final ClientService clientService;

   private final Logger logger = Logger.getLogger("register");

   @PostMapping("/register")
   public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws EmailAlreadyUsedException {
      logger.info(registerRequest.toString());
      return ResponseEntity.ok(clientService.register(registerRequest));
   }

}
