package com.example.gestionDeCovoiturage.controllers.auth;

import com.example.gestionDeCovoiturage.dto.auth.LoginRequest;
import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class ClientAuthController {

   private final ClientService clientService;


   @GetMapping("/login")
   public String showFormLogin() {
      return "auth/login";
   }

   @GetMapping("/login-error")
   public String errorLogin(Model model){
      model.addAttribute("error", true);
      return "auth/login";
   }

   @GetMapping("/register")
   public String showRegisterForm() {
      return "auth/register";
   }





}
