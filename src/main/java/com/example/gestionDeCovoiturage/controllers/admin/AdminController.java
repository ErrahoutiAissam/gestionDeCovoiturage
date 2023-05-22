package com.example.gestionDeCovoiturage.controllers.admin;

import com.example.gestionDeCovoiturage.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
      model.addAttribute("clients",clientService.getAll(page, size, keyword));
      return "admin/utilisateurs/all";
   }

   @GetMapping("/create")
   public String create() {
      return "admin/utilisateurs/create";
   }

}
