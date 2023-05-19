package com.example.gestionDeCovoiturage.dto.trajet;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.models.Reservation;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

public class TrajetDTO {
    private Long id;
    private String villeDepart;
    private String villeArrive;
    private Date dateDepart;
    private Integer nbrPlaceDisponible;
    private Double prixParPersonne;
    private List<ReservationDTO> reservations;
}
