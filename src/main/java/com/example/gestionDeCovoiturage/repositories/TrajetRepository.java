package com.example.gestionDeCovoiturage.repositories;

import com.example.gestionDeCovoiturage.models.Trajet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet,Long> {


    Page<Trajet> findByVilleDepartContaining(String villeDepart, Pageable pageable);



}
