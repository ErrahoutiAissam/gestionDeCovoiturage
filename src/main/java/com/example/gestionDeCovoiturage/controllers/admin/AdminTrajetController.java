package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.service.TrajetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminTrajetController {


   private final TrajetService trajetService;

   @GetMapping("/trajets")
   public String allTrajets(
           Model model,
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword
   ) {
      model.addAttribute(trajetService.getAll(page, size, keyword));
      return "admin/trajets/all";
   }

   @GetMapping("trajets-proposes")
   public String trajetsProposes(
           Model model,
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword
   ) {
//      model.addAttribute(trajetService.getAll(page, size, keyword));
      return "admin/trajets/proposes";
   }



}
