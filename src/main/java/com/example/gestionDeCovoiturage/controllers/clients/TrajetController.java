package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.TrajetService;
import com.example.gestionDeCovoiturage.utils.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class TrajetController {

   private final TrajetService trajetService;


   @GetMapping("/trajets")
   public String allTrajets(
           Model model,
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "30") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword
   ) {
      model.addAttribute("trajets",trajetService.getAll(page, size, keyword));
      return "client/trajets/all";
   }

   @GetMapping("/trajets-proposes")
   public String AllTrajetsProposes(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "30") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword,

           Model model) {
        model.addAttribute("trajetsProposes",trajetService.getProposes(page,size,keyword));
      return "client/trajets/proposes";
   }


   @GetMapping("/trajets-create")
   public String showCreationForm() {
      return "client/trajets/create";
   }

   @GetMapping("/Selected/{id}")
   public String trajetSelected(@PathVariable Long id, Model model) throws NotFoundException {
      model.addAttribute("trajetSelected", trajetService.getById(id));
      System.out.println(trajetService.getById(id).getReservations());
      return "client/trajets/trajetInfos";
   }

   @GetMapping("/trajets/{id}/confirm")
   public String confirmeRes(
           @PathVariable Long id,
           @RequestParam("resId") Long resId
   ) throws NotFoundException {
      trajetService.confirmState(id, resId);
      return "redirect:/client/Selected/"+id;
   }


}
