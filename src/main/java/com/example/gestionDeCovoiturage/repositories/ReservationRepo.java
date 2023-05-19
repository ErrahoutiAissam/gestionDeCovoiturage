package com.example.gestionDeCovoiturage.repositories;


import com.example.gestionDeCovoiturage.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {

}
