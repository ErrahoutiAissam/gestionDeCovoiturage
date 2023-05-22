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
	private  PasswordEncoder passwordEncoder;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	public static void main(String[] args) {
		SpringApplication.run(GestionDeCovoiturageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Utilisateur utilisateur = new Utilisateur();
//		utilisateur.setNom("bouali");
//		utilisateur.setPrenom("salma");
//		utilisateur.setEmail("bouali@gmail.com");
//		utilisateur.setPassword(passwordEncoder.encode("12345678"));
//		utilisateur.setRole(Role.CLIENT);
//
//		utilisateurRepository.save(utilisateur);

	}
}
