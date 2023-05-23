package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.TrajetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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





}
