package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationIdDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationMapper;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/reservation")
public class AdminReservationRestController {


    private final ReservationService reservationService;

    private final ReservationMapper reservationMapper;

    @GetMapping
    public ResponseEntity<?> getReservations(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword
    ){
    return ResponseEntity.ok(reservationService.getAll(page, size, keyword));
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(reservationService.getById(id));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody ReservationIdDTO res) throws NotFoundException, AlreadyExistException {

        return ResponseEntity.ok(reservationService.createFromIds(res));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody ReservationIdDTO resDTO) throws NotFoundException {
//        System.out.println(resDTO);
//        System.out.println(resDTO.getIdTrajet());
        System.out.println(resDTO.getEtat());
        return ResponseEntity.ok(reservationService.update(resDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) throws NotFoundException {
        reservationService.delete(id);
        return ResponseEntity.ok().build();
    }

}
