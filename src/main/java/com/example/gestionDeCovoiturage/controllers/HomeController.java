package com.example.gestionDeCovoiturage.controllers;

import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.ClientService;
import com.example.gestionDeCovoiturage.utils.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
   private final ClientService clientService;

   @GetMapping("/index")
   public String home(Model model) throws NotFoundException {

      model.addAttribute("user",Principal.getCurrentUser());
      model.addAttribute("image",clientService.getUserById(
              Principal.getCurrentUser().getId()).getImageUrl());
      return "index";
   }

   @GetMapping({"/",""})
   public String redirectToHome() {
      return "redirect:/index";
   }

}
