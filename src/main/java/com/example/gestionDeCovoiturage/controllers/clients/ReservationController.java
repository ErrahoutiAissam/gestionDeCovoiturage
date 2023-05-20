package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/clients/reservation")
public class ReservationController {

    private final ReservationService reservationService;


    @GetMapping
    public List<ReservationDTO> showReservations(){
        return reservationService.getAll();
    }


}
