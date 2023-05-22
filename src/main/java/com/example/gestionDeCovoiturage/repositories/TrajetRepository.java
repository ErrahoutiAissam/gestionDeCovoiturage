package com.example.gestionDeCovoiturage.repositories;

import com.example.gestionDeCovoiturage.models.Trajet;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet,Long> {


    List<Trajet> findByVilleDepartOrVilleArrive(String villeDepart, String villeArrive, Pageable pageable);
    List<Trajet> findByDateDepartLessThan(Date date);


}
