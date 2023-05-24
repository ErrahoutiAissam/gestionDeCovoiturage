package com.example.gestionDeCovoiturage.controllers;

import com.example.gestionDeCovoiturage.utils.Principal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

   @GetMapping("/index")
   public String home(Model model) {

      model.addAttribute("user",Principal.getCurrentUser());
      model.addAttribute("image",Principal.getImage());
      return "index";
   }

   @GetMapping({"/",""})
   public String redirectToHome() {
      return "redirect:/index";
   }

}
