package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
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
    public ResponseEntity<?> getAllTrajets(){
        return ResponseEntity.ok(trajetService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrajetById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(trajetService.getById(id));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createTrajet(@RequestBody TrajetDTO trajetDTO) throws AlreadyExistException {
        return ResponseEntity.ok(trajetService.create(trajetDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrajet(@PathVariable Long id, @RequestBody TrajetDTO trajetDTO) throws NotFoundException {
        return ResponseEntity.ok(trajetService.update(trajetDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrajet(@PathVariable Long id) throws NotFoundException {
        trajetService.deleteTrajet(id);
        return ResponseEntity.ok("Trajet supprimé avec succès");
    }
}
