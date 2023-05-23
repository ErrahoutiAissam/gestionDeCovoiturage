package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.exceptions.notfound.UserNotFoundException;
import com.example.gestionDeCovoiturage.service.TrajetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trajets")
public class TrajetRestController {

   private final TrajetService trajetService;


   @GetMapping
   public ResponseEntity<?> getAll(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword) {

      return ResponseEntity.ok(trajetService.getAll(page, size, keyword));
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> getTrajetById(@PathVariable Long id) throws NotFoundException {
      return ResponseEntity.ok(trajetService.getById(id));
   }


   @PostMapping("/create")
   public ResponseEntity<?> createTrajet(@RequestBody TrajetDTO trajetDTO) throws AlreadyExistException {
      System.out.println(trajetDTO);
      return ResponseEntity.ok(trajetService.create(trajetDTO));
   }


   @PutMapping("/update")
   public ResponseEntity<?> update(@RequestBody TrajetDTO trajetDTO) throws NotFoundException {
      System.out.println(trajetDTO);
      trajetService.update(trajetDTO);
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteTrajet(@PathVariable Long id) throws NotFoundException {
      trajetService.deleteTrajet(id);
      return ResponseEntity.ok("Trajet supprimé avec succès");
   }
}
