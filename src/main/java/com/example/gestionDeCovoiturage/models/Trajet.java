package com.example.gestionDeCovoiturage.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String villeDepart;
    private String villeArrive;
    private Date dateDepart;
    private Integer nbrPlaceDisponible;
    private Double prixParPersonne;
    @OneToMany
    private List<Reservation> reservations;

}
