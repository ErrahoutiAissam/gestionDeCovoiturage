package com.example.gestionDeCovoiturage.repositories;

import com.example.gestionDeCovoiturage.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajetRepo extends JpaRepository<Trajet,Long> {
}
