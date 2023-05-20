package com.example.gestionDeCovoiturage.controllers.auth;

import com.example.gestionDeCovoiturage.dto.auth.LoginRequest;
import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.EmailAlreadyUsedException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.exceptions.notfound.UserNotFoundException;
import com.example.gestionDeCovoiturage.security.JwtUtils;
import com.example.gestionDeCovoiturage.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class ClientAuthController {

   private final ClientService clientService;


   @PostMapping("/register")
   public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) throws AlreadyExistException {
      return ResponseEntity.ok(clientService.register(request));
   }


   @PostMapping("/login")
   public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) throws NotFoundException {
      return ResponseEntity.ok(clientService.login(request));
   }

}
