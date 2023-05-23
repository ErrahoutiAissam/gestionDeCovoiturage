package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.service.ClientService;
import com.example.gestionDeCovoiturage.utils.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {


   private final ClientService clientService;

   @GetMapping("/{id}")
   public String profile(@PathVariable Long id, Model model) throws NotFoundException {
      model.addAttribute("user", clientService.getUserById(id));
      return "admin/utilisateurs/profile";
   }



}
