package com.example.gestionDeCovoiturage.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'EN_ATTENTE'")
    private EtatReservation etat;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", utilisateur=" + utilisateur +
                ", trajet=" + trajet +
                ", etat=" + etat +
                '}';
    }
}
