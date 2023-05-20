package com.example.gestionDeCovoiturage;

import com.example.gestionDeCovoiturage.models.EtatReservation;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Role;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionDeCovoiturageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeCovoiturageApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner mappingDemo(ReservationRepository reservationRepository, UtilisateurRepository utilisateurRepository){
//
//		return args -> {
//			Reservation reservation = new Reservation();
//
//			Utilisateur utilisateur = new Utilisateur();
//			utilisateur.setNom("errahouti");
//			utilisateur.setPrenom("Aissam");
//			utilisateur.setEmail("errahouti@gmail.com");
//			utilisateur.setPassword("1234");
//			utilisateur.setRole(Role.CLIENT);
//
//			utilisateurRepository.save(utilisateur);
//
//			reservation.setUtilisateur(utilisateur);
//			reservation.setEtat(EtatReservation.CONFIRME);
//
//			reservationRepository.save(reservation);
//		};
//	}

}
