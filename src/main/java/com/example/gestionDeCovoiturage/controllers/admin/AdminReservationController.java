package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminReservationController {

   private final ReservationService reservationService;

   @GetMapping("/reservations")
   public String allReservations(
           Model model,
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword
   ) {
      model.addAttribute(reservationService.getAll(page, size, keyword));
      return "admin/trajets/all";
   }

}
