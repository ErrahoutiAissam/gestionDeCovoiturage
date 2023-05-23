package com.example.gestionDeCovoiturage.dto.reservation;


import com.example.gestionDeCovoiturage.models.EtatReservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservationIdDTO {


    private Long idTrajet;

    private EtatReservation etat;

}
