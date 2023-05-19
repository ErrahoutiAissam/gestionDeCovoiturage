package com.example.gestionDeCovoiturage.dto.reservation;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;


public class ReservationDTO {
    private Long id;

    private UtilisateurDTO utilisateur;

    private TrajetDTO trajet;
}
