package com.example.gestionDeCovoiturage.service;


import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.EmailAlreadyUsedException;
import com.example.gestionDeCovoiturage.models.Role;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import com.example.gestionDeCovoiturage.security.MyPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UtilisateurRepository utilisateurRepo;
    private MyPasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest) throws EmailAlreadyUsedException {
        if(utilisateurRepo.existsByEmail(registerRequest.getEmail()))
            throw new EmailAlreadyUsedException();
        Utilisateur utilisateur= new Utilisateur();
        utilisateur.setNom(registerRequest.getNom());
        utilisateur.setPrenom(registerRequest.getPrenom());
        utilisateur.setEmail(registerRequest.getEmail());
        utilisateur.setPassword(passwordEncoder.passwordEncoder().encode(registerRequest.getPassword()));
        utilisateur.setRole(Role.ADMIN);
        utilisateurRepo.save(utilisateur);
    }
}
