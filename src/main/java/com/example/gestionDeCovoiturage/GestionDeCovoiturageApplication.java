package com.example.gestionDeCovoiturage;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationMapper;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.dto.user.UserMapper;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.*;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import com.example.gestionDeCovoiturage.service.ClientService;
import com.example.gestionDeCovoiturage.service.TrajetService;
import jdk.jfr.Unsigned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GestionDeCovoiturageApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private TrajetRepository trajetRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TrajetMapper trajetMapper;

	@Autowired
	private TrajetService trajetService;

	@Autowired
	private ReservationMapper reservationMapper;


	@Autowired
	private ReservationRepository reservationRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionDeCovoiturageApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
////		Reservation reservation = new Reservation();
////
////		reservation.setUtilisateur(utilisateurRepository.getReferenceById(7L));
////		reservation.setTrajet(trajetRepository.getReferenceById(2L));
////		reservation.setEtat(EtatReservation.EN_ATTENTE);
////
////		reservationRepository.save(reservation);
//
////		Utilisateur user = userMapper.utilisateurDTOToUtilisateur(clientService.getUserById(7L));
//		List<Reservation> reservations = reservationRepository.findByUtilisateurId(7L);
//		List<ReservationDTO> reservationDTOs = reservations.stream()
//				.map(reservation -> {
//					ReservationDTO reservationDTO = reservationMapper.ResTOResDTO(reservation);
//					Utilisateur user = null;
//					Trajet trajet = null;
//					try {
//						user = userMapper.utilisateurDTOToUtilisateur(clientService.getUserById(reservation.getUtilisateur().getId()));
//						trajet = trajetMapper.createTrajet(trajetService.getById(reservation.getTrajet().getId()));
//					} catch (NotFoundException e) {
//						throw new RuntimeException(e);
//					}
//
//					reservationDTO.setUtilisateur(userMapper.utilisateurToUtilisateurDTO(user));
//					reservationDTO.setTrajet(trajetMapper.trajetToTrajetDTO(trajet));
//					return reservationDTO;
//				})
//
//				.collect(Collectors.toList());
//
//		System.out.println(reservationDTOs);



	}
}

