package com.example.gestionDeCovoiturage.dto.trajet;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TrajetDTO {
    private Long id;
    private String villeDepart;
    private String villeArrive;
    private Date dateDepart;
    private Integer nbrPlaceDisponible;
    private Double prixParPersonne;
    private List<ReservationDTO> reservations;
}
