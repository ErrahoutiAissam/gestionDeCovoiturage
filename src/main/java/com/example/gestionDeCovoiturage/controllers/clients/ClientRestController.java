package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
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
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientRestController {

   private final ImageService imageService;

   private final ClientService clientService;


   @GetMapping
   public ResponseEntity<?> allClients(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword) {

      return ResponseEntity.ok(clientService.getAll(page, size, keyword));
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> getClientById(@PathVariable("id") Long id) throws NotFoundException {
      return ResponseEntity.ok(clientService.getUserById(id));
   }


   @PostMapping("/{id}/image")
   public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws NotFoundException, IOException {
      imageService.uploadImage(id, file);
      return ResponseEntity.ok("uploaded successfully !!");
   }

   @PutMapping("/{id}/image")
   public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws NotFoundException, IOException {
      imageService.uploadImage(id, file);
      return ResponseEntity.ok("updated successfully !!");
   }

   @DeleteMapping("/{id}/image")
   public ResponseEntity<?> deleteImage(@PathVariable Long id) throws NotFoundException {
      imageService.deleteImage(id);
      return ResponseEntity.ok("deleted successfully !!");
   }

   @PutMapping("/update")
   public ResponseEntity<?> update(@RequestBody UtilisateurDTO utilisateurDTO) throws UserNotFoundException {
      System.out.println(utilisateurDTO);
      clientService.update(utilisateurDTO);
      return ResponseEntity.ok().build();
   }


  @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) throws UserNotFoundException {
      clientService.delete(id);
      return ResponseEntity.ok().build();
  }


}
