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
import com.example.gestionDeCovoiturage.security.JwtUtils;
import com.example.gestionDeCovoiturage.security.MyPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ClientService {

   private final UtilisateurRepository utilisateurRepository;
   private final MyPasswordEncoder passwordEncoder;
   private final UserMapper userMapper;

   private final JwtUtils jwtUtils;

   private final AuthenticationManager authenticationManager;


   public UtilisateurDTO getUserById(Long id) throws NotFoundException {
      return userMapper.toUtilisateurResponseDTO(utilisateurRepository.findById(id).orElseThrow(NotFoundException::new));
   }


   public UtilisateurDTO register(RegisterRequest registerRequest) throws EmailAlreadyUsedException {
      if (utilisateurRepository.existsByEmail(registerRequest.getEmail()))
         throw new EmailAlreadyUsedException();

      Utilisateur utilisateur = userMapper.registerRequestToUtilisateur(registerRequest);
      utilisateur.setPassword(passwordEncoder.passwordEncoder().encode(registerRequest.getPassword()));
      utilisateur.setRole(Role.CLIENT);
      return userMapper.utilisateurToUtilisateurDTO(utilisateurRepository.save(utilisateur));
   }

   public LoginResponse login(LoginRequest request) throws UserNotFoundException {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
              .orElseThrow(UserNotFoundException::new);

      String accessToken = jwtUtils.generateAccessToken(utilisateur);
      String refreshToken = jwtUtils.generateRefreshToken(utilisateur);
      return LoginResponse.builder()
              .accessToken(accessToken)
              .refreshToken(refreshToken)
              .build();
   }




}
