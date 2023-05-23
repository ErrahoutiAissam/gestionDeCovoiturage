package com.example.gestionDeCovoiturage.repositories;

import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.models.Trajet;

import com.example.gestionDeCovoiturage.models.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet,Long> {


    List<Trajet> findByVilleDepartOrVilleArriveContains(String villeDepart, String villeArrive, Pageable pageable);

    Page<Trajet> findAllByProposeur(Utilisateur utilisateur, Pageable pageable);


}
