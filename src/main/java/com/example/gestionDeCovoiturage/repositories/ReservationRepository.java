package com.example.gestionDeCovoiturage.repositories;


import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.models.EtatReservation;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Trajet;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findByEtat(EtatReservation etatReservation, Pageable pageable);

    Page<Reservation> findAllByOrderByEtat(PageRequest pageRequest);

    List<Reservation> findByUtilisateurId(Long id);
}
