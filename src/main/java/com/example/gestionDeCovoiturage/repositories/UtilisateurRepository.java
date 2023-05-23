package com.example.gestionDeCovoiturage.repositories;

import com.example.gestionDeCovoiturage.models.Utilisateur;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);

    List<Utilisateur> findByNomOrPrenomOrEmailContains(String nom, String prenom, String email, Pageable pageable);

//    Optional<Utilisateur> findById(Long id);

}
