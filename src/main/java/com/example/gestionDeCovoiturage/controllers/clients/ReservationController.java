package com.example.gestionDeCovoiturage.controllers.clients;


import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.dto.user.UserMapper;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.service.ClientService;
import com.example.gestionDeCovoiturage.service.ReservationService;
import com.example.gestionDeCovoiturage.service.TrajetService;
import com.example.gestionDeCovoiturage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/client/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    private final ClientService clientService;

    private final TrajetService trajetService;

    private final ReservationRepository reservationRepository;



    @GetMapping
    public String showAllReservationsPage(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword) throws NotFoundException {


            List <ReservationDTO> reservationList =  reservationService.findAll(page, size);
            List<Reservation> list = reservationRepository.findAll();



//            for (ReservationDTO res : reservationList){
//                UtilisateurDTO user = clientService.getUserById(res.getUtilisateur().getId());
//                TrajetDTO trajet = trajetService.getById(res.getTrajet().getId());
//                res.setUtilisateur(user);
//                res.setTrajet(trajet);
//            }
        System.out.println(list);
        System.out.println(reservationList);
            model.addAttribute("reservations", list);
        return "client/reservations/all";
    }

    @GetMapping("/create")
    public String showAddReservationForm(Model model) {
        model.addAttribute("reservationDTO", new ReservationDTO());
        return "client/reservations/create";
    }

    @GetMapping("{id}")
    public String showReservationPage(@PathVariable Long id , Model model) throws NotFoundException {
            ReservationDTO reservationDTO = reservationService.getById(id);
            model.addAttribute("reservation",reservationDTO);
            return "client/reservations/reservation";

    }





}
