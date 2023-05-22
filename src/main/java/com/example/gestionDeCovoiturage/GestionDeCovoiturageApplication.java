package com.example.gestionDeCovoiturage;

import com.example.gestionDeCovoiturage.models.EtatReservation;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Role;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionDeCovoiturageApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private TrajetRepository trajetRepository;


	@Autowired
	private ReservationRepository reservationRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionDeCovoiturageApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		Reservation reservation = new Reservation();
//
//		reservation.setUtilisateur(utilisateurRepository.getReferenceById(7L));
//		reservation.setTrajet(trajetRepository.getReferenceById(2L));
//		reservation.setEtat(EtatReservation.EN_ATTENTE);
//
//		reservationRepository.save(reservation);


	}
}

