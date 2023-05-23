package com.example.gestionDeCovoiturage;

import com.example.gestionDeCovoiturage.models.*;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

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
//
//		Utilisateur utilisateur = new Utilisateur(null,"client1","client2","test@test.com",
//				passwordEncoder.encode("12345678"),null,Role.CLIENT);
//
//		utilisateurRepository.save(utilisateur);

		Trajet trajet=new Trajet(null,"v3","v4",new Date(),3,10.,utilisateurRepository.getReferenceById(2L),null);
		trajetRepository.save(trajet);
	}
}

