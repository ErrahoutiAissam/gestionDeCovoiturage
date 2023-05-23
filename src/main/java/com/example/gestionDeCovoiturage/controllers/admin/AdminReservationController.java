package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationMapper;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.dto.user.UserMapper;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/reservations")
@RequiredArgsConstructor
public class AdminReservationController {

   private final ReservationService reservationService;



   private final ClientService clientService;

   private final UserMapper userMapper;

   private final TrajetMapper trajetMapper;

   private final ReservationMapper reservationMapper;

   private final TrajetService trajetService;



   @GetMapping
   public String allReservations(
           Model model,
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword
   ) {

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

      return "admin/reservations/all";
   }

    @GetMapping("/create")
    public String showAddReservationForm(Model model) {
        List<TrajetDTO> trajets = trajetService.findAll(0, 10);
        model.addAttribute("trajets", trajets);
        model.addAttribute("users",clientService.findAll(0,10) );
        model.addAttribute("reservation", new ReservationDTO());

        return "admin/reservations/create";
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


        model.addAttribute("trajets", trajets);
        model.addAttribute("users",clientService.findAll(0,10));
        model.addAttribute("singleReservation", reservationDTO);

        return "admin/reservations/reservation";
    }



}
