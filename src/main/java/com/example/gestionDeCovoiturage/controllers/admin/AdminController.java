package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.service.ClientService;
import com.example.gestionDeCovoiturage.utils.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/clients")
@RequiredArgsConstructor
public class AdminController {


   private final ClientService clientService;

   @GetMapping
   public String allClients(
           Model model,
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @RequestParam(name = "keyword", defaultValue = "") String keyword) {

      List<UtilisateurDTO> clients = clientService.getAll(page, size, keyword);
      model.addAttribute("clients",clients);
      return "admin/utilisateurs/all";
   }

   @GetMapping("/create")
   public String create(Model model) {
      return "admin/utilisateurs/create";
   }

}
