package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateurRepo.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException(String.format("utilisateur avec email %s introuvable",username)));
    }
}
