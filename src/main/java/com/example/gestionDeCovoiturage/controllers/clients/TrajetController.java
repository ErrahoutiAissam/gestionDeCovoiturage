package com.example.gestionDeCovoiturage.controllers.clients;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.service.TrajetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/trajets")
@RequiredArgsConstructor
public class TrajetController {

    private final TrajetService trajetService;

    @GetMapping
    public String alltrajets(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            Model model) {
        Page<TrajetDTO> trajetsPage = trajetService.getTrajetsPage(page, size, keyword);
        model.addAttribute("trajetsPage", trajetsPage);
        model.addAttribute("pages", new int[trajetsPage.getTotalPages()]);
        model.addAttribute("currentPage" ,page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("max", trajetsPage.getTotalPages() -1 );
        model.addAttribute("size" ,size);
        return "client/trajets/proposes";
    }
}
