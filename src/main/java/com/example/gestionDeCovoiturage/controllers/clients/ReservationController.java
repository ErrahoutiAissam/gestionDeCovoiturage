package com.example.gestionDeCovoiturage.controllers.clients;


import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationMapper;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.dto.user.UserMapper;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Trajet;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.service.ClientService;
import com.example.gestionDeCovoiturage.service.ReservationService;
import com.example.gestionDeCovoiturage.service.TrajetService;
import com.example.gestionDeCovoiturage.utils.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping("/client/reservations")
public class ReservationController {

   private final ReservationService reservationService;


   private final ClientService clientService;

   private final UserMapper userMapper;

   private final TrajetMapper trajetMapper;

   private final ReservationMapper reservationMapper;

   private final TrajetService trajetService;


   @GetMapping
   public String showAllReservationsPage(
           Model model,
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword) throws NotFoundException {


      List<Reservation> reservations = reservationService.getAllReservations();

      List<ReservationDTO> reservationDTOs = reservations.stream()
              .map(reservation -> {
                 ReservationDTO reservationDTO = reservationMapper.ResTOResDTO(reservation);
                 Utilisateur user = null;
                 Trajet trajet = null;
                 try {
                    user = userMapper.utilisateurDTOToUtilisateur(clientService.getUserById(reservation.getUtilisateur().getId()));
                    trajet = trajetMapper.createTrajet(trajetService.getById(reservation.getTrajet().getId()));
                 } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                 }

                 reservationDTO.setUtilisateur(userMapper.utilisateurToUtilisateurDTO(user));
                 reservationDTO.setTrajet(trajetMapper.trajetToTrajetDTO(trajet));
                 return reservationDTO;
              })
              .collect(Collectors.toList());

      System.out.println(reservationDTOs);
      model.addAttribute("reservations", reservationDTOs);

      return "client/reservations/all";
   }

   @GetMapping("/create")
   public String showAddReservationForm(Model model) {
      List<TrajetDTO> trajets = trajetService.findAll(0, 10);
      model.addAttribute("trajets", trajets);
      model.addAttribute("user", Principal.getCurrentUser());
      model.addAttribute("reservation", new ReservationDTO());

      return "client/reservations/create";
   }

   @GetMapping("/{id}")
   public String showReservation(Model model, @PathVariable Long id) throws NotFoundException {

      List<TrajetDTO> trajets = trajetService.findAll(0, 10);

      Reservation reservation = reservationService.getById2(id);
      ReservationDTO reservationDTO = reservationMapper.ResTOResDTO(reservation);


      Utilisateur utilisateur = userMapper.utilisateurDTOToUtilisateur(clientService.getUserById(reservation.getUtilisateur().getId()));
      Trajet trajet = trajetMapper.createTrajet(trajetService.getById(reservation.getTrajet().getId()));

      reservationDTO.setUtilisateur(userMapper.utilisateurToUtilisateurDTO(utilisateur));
      reservationDTO.setTrajet(trajetMapper.trajetToTrajetDTO(trajet));

      System.out.println(reservationDTO);


      model.addAttribute("trajets", trajets);
      model.addAttribute("singleReservation", reservationDTO);

      return "client/reservations/reservation";

   }


}
