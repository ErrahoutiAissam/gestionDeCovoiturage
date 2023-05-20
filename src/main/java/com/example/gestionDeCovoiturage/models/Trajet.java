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

    @Column(nullable = false)
    private String villeDepart;

    @Column(nullable = false)
    private String villeArrive;

    @Column(nullable = false)
    private Date dateDepart;

    @Column(nullable = false)
    private Integer nbrPlacesDisponibles;

    @Column(nullable = false)
    private Double prixParPersonne;

    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

}
