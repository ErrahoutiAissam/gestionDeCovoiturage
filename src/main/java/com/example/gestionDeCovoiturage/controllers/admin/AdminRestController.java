package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.EmailAlreadyUsedException;
import com.example.gestionDeCovoiturage.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/clients")
@RequiredArgsConstructor
public class AdminRestController {

   private final ClientService clientService;


   @GetMapping
   public ResponseEntity<?> allClients(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword) {

      return ResponseEntity.ok(clientService.getAll(page, size, keyword));
   }

   @PostMapping("/create")
   public ResponseEntity<?> create(@RequestBody RegisterRequest registerRequest) throws EmailAlreadyUsedException {
      return ResponseEntity.ok(clientService.create(registerRequest));
   }

}
