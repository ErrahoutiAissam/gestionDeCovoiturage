package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.exceptions.notfound.UserNotFoundException;
import com.example.gestionDeCovoiturage.service.ClientService;
import com.example.gestionDeCovoiturage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {

   private final ImageService imageService;

   private final ClientService clientService;

   @GetMapping("/{id}")
   public ResponseEntity<?> getClientById(@PathVariable("id") Long id) throws NotFoundException {
      return ResponseEntity.ok(clientService.getUserById(id));
   }


   @PostMapping("/{id}/image")
   public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws UserNotFoundException, IOException {
      imageService.uploadImage(id, file);
      return ResponseEntity.ok("uploaded successfully !!");
   }

   @PutMapping("/{id}/image")
   public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws UserNotFoundException, IOException {
      imageService.uploadImage(id, file);
      return ResponseEntity.ok("updated successfully !!");
   }

   @DeleteMapping("/{id}/image")
   public ResponseEntity<?> deleteImage(@PathVariable Long id) throws UserNotFoundException {
      imageService.deleteImage(id);
      return ResponseEntity.ok("deleted successfully !!");
   }

}
