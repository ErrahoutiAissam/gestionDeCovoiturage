package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.TrajetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/trajets")
@RequiredArgsConstructor
public class AdminTrajetRestController {

   private final TrajetService trajetService;


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
   public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
      trajetService.deleteTrajet(id);
      return ResponseEntity.ok().build();
   }

   @PostMapping("/{trajetId}/add-reservation")
   public ResponseEntity<?> addReservationsToTrajet(
           @PathVariable Long trajetId,
           @RequestBody List<Long> selectedIds) throws NotFoundException {
      System.out.println(trajetId + " " + selectedIds);
      trajetService.addReservationsToTrajet(trajetId, selectedIds);
      return ResponseEntity.ok().build();
   }


   @DeleteMapping("/{trajetId}/remove")
   public ResponseEntity<?> removeReservationFromTrajet(
           @PathVariable Long trajetId,
           @RequestParam("resId") Long resId
           ) throws NotFoundException {
      trajetService.removeReservationFromTrajet(trajetId, resId);
      return ResponseEntity.ok().build();
   }

   @PutMapping("/{trajetId}/reservations/{resId}")
   public ResponseEntity<?> confirmState(
           @PathVariable Long trajetId,
           @PathVariable Long resId
           ) throws NotFoundException {
      if(trajetService.confirmState(trajetId, resId)) {
         return ResponseEntity.ok().build();
      }
      return ResponseEntity.badRequest().build();
   }





}
