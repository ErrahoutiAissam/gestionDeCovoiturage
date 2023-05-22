package com.example.gestionDeCovoiturage.dto.reservation;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.models.EtatReservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservationDTO {
    private Long id;

    private UtilisateurDTO utilisateur;

    private TrajetDTO trajet;


    // why create a dto if all the attributes are the same ?
    private EtatReservation etat;


}
