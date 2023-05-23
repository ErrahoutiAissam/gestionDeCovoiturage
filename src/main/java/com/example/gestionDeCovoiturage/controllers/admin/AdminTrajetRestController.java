package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.service.TrajetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
