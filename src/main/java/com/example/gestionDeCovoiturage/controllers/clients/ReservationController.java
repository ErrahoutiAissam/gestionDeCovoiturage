package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationMapper;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/clients/reservation")
public class ReservationController {


    private final ReservationService reservationService;



    @GetMapping
    public ResponseEntity<?> getReservations(){
        return ResponseEntity.ok(reservationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(reservationService.getById(id));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) throws AlreadyExistException {
         return ResponseEntity.ok(reservationService.createReservation(reservationDTO));
    }

   @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) throws NotFoundException {
        return ResponseEntity.ok(reservationService.update(reservationDTO, id));
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) throws NotFoundException {
         reservationService.delete(id);
        return ResponseEntity.ok("Reservation deleted succesfully");
   }

}
