package com.example.gestionDeCovoiturage.service;


import com.example.gestionDeCovoiturage.dto.auth.LoginRequest;
import com.example.gestionDeCovoiturage.dto.auth.LoginResponse;
import com.example.gestionDeCovoiturage.dto.auth.RegisterRequest;
import com.example.gestionDeCovoiturage.dto.user.UserMapper;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.EmailAlreadyUsedException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.exceptions.notfound.UserNotFoundException;
import com.example.gestionDeCovoiturage.models.Role;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import com.example.gestionDeCovoiturage.security.MyPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

   private final UtilisateurRepository utilisateurRepository;
   private final MyPasswordEncoder passwordEncoder;
   private final UserMapper userMapper;


   public UtilisateurDTO getUserById(Long id) throws NotFoundException {
      return userMapper.toUtilisateurResponseDTO(utilisateurRepository.findById(id).orElseThrow(NotFoundException::new));
   }


   public List<UtilisateurDTO> findAll(int page, int size) {
      return utilisateurRepository.findAll(PageRequest.of(page, size))
              .getContent().stream()
              .map(userMapper::toUtilisateurResponseDTO)
              .collect(Collectors.toList());
   }

   public List<UtilisateurDTO> findByKeyword(int page, int size, String keyword) {
      return utilisateurRepository.findByNomOrPrenomOrEmailContains(keyword,keyword,keyword,PageRequest.of(page, size))
              .stream().map(userMapper::toUtilisateurResponseDTO)
              .collect(Collectors.toList());
   }

   public List<UtilisateurDTO> getAll(int page, int size, String keyword) {
      return keyword.isEmpty() ?
              findAll(page, size) : findByKeyword(page, size, keyword);
   }

   public UtilisateurDTO register(RegisterRequest registerRequest) throws EmailAlreadyUsedException {
      if (utilisateurRepository.existsByEmail(registerRequest.getEmail()))
         throw new EmailAlreadyUsedException();

      Utilisateur utilisateur = userMapper.registerRequestToUtilisateur(registerRequest);
      utilisateur.setPassword(passwordEncoder.passwordEncoder().encode(registerRequest.getPassword()));
      utilisateur.setRole(Role.CLIENT);
      return userMapper.utilisateurToUtilisateurDTO(utilisateurRepository.save(utilisateur));
   }


   public UtilisateurDTO create(RegisterRequest registerRequest) throws EmailAlreadyUsedException {
      if (utilisateurRepository.existsByEmail(registerRequest.getEmail()))
         throw new EmailAlreadyUsedException();
      System.out.println(registerRequest);
      Utilisateur utilisateur = userMapper.registerRequestToUtilisateur(registerRequest);
      utilisateur.setPassword(passwordEncoder.passwordEncoder().encode(registerRequest.getPassword()));
      utilisateur.setRole(
              registerRequest.getRole().equals("client") ?
                      Role.CLIENT : Role.ADMIN
      );
      return userMapper.utilisateurToUtilisateurDTO(utilisateurRepository.save(utilisateur));
   }


   public void update(UtilisateurDTO utilisateurDTO) throws UserNotFoundException {
      Utilisateur utilisateur = utilisateurRepository.findById(utilisateurDTO.getId()).orElseThrow(UserNotFoundException::new);
      utilisateur.setNom(utilisateurDTO.getNom());
      utilisateur.setPrenom(utilisateurDTO.getPrenom());
      utilisateurRepository.save(utilisateur);
   }

   public void delete(Long id) throws UserNotFoundException {
      Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(UserNotFoundException::new);
      utilisateurRepository.delete(utilisateur);
   }





}
