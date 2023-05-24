package com.example.gestionDeCovoiturage.controllers.auth;

import com.example.gestionDeCovoiturage.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

   private final ClientService clientService;


   @GetMapping("/login")
   public String showFormLogin() {
      return "auth/login";
   }

//   @GetMapping("/login-error")
//   public String errorLogin(Model model){
//      model.addAttribute("error", true);
//      return "auth/login";
//   }

   @GetMapping("/register")
   public String showRegisterForm() {
      return "auth/register";
   }





}
