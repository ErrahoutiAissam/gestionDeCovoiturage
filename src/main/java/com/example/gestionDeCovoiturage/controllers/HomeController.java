package com.example.gestionDeCovoiturage.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

   @GetMapping("/index")
   public String home() {

      return "index";
   }

   @GetMapping({"/",""})
   public String redirectToHome() {
      return "redirect:/index";
   }

}
