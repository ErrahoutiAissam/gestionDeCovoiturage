package com.example.gestionDeCovoiturage.controllers.auth;

import com.example.gestionDeCovoiturage.dto.auth.LoginRequest;
import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth/admin")
@RequiredArgsConstructor
public class AdminAuthController {

   private final AdminService adminService;

   @PostMapping("/register")
   public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) throws AlreadyExistException {
      return ResponseEntity.ok(adminService.register(request));
   }


   @PostMapping("/login")
   public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) throws NotFoundException {
      return ResponseEntity.ok(adminService.login(request));
   }

   //TODO: new access-token

}
