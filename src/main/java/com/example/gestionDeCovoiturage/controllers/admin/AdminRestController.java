package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
