package com.example.gestionDeCovoiturage.controllers.clients;


import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public String showAllReservationsPage(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword){

        Page<ReservationDTO> reservationsPage = reservationService.getReservationPage(page, size, keyword);
        model.addAttribute("reservationsPage", reservationsPage);
        model.addAttribute("pages", new int[reservationsPage.getTotalPages()]);
        model.addAttribute("currentPage" ,page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("max", reservationsPage.getTotalPages() -1 );
        model.addAttribute("size" ,size);

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
