package com.example.gestionDeCovoiturage.service;


import com.example.gestionDeCovoiturage.dto.auth.LoginRequest;
import com.example.gestionDeCovoiturage.dto.auth.LoginResponse;
import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.dto.user.UserMapper;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.EmailAlreadyUsedException;
import com.example.gestionDeCovoiturage.exceptions.notfound.UserNotFoundException;
import com.example.gestionDeCovoiturage.models.Role;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import com.example.gestionDeCovoiturage.security.MyPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UtilisateurRepository utilisateurRepository;
    private final MyPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    public UtilisateurDTO register(RegisterRequest registerRequest) throws EmailAlreadyUsedException {
        if(utilisateurRepository.existsByEmail(registerRequest.getEmail()))
            throw new EmailAlreadyUsedException();
        Utilisateur utilisateur = userMapper.registerRequestToUtilisateur(registerRequest);
        utilisateur.setPassword(passwordEncoder.passwordEncoder().encode(registerRequest.getPassword()));
        utilisateur.setRole(Role.ADMIN);
        return userMapper.utilisateurToUtilisateurDTO(utilisateurRepository.save(utilisateur));
    }



}
