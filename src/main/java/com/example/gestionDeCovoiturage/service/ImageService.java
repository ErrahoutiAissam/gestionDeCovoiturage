package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.exceptions.notfound.UserNotFoundException;
import com.example.gestionDeCovoiturage.models.Image;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.ImageRepository;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import com.example.gestionDeCovoiturage.utils.NameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

   private final UtilisateurRepository utilisateurRepository;
   private final FirebaseStorageService firebaseStorageService;

   private final ImageRepository imageRepository;



   public void uploadImage(Long userID, MultipartFile multipartFile) throws UserNotFoundException, IOException {
      Utilisateur utilisateur = utilisateurRepository.findById(userID).orElseThrow(UserNotFoundException::new);
      String fileName = NameGenerator.generateUniqueFileName();
      String imageUrl = firebaseStorageService.uploadImage(multipartFile, fileName);
      Image image = Image.builder()
              .nom(fileName)
              .url(imageUrl)
              .build();
      if(utilisateur.getImage() != null) deleteImage(utilisateur.getId());
      utilisateur.setImage(imageRepository.save(image));
      utilisateurRepository.save(utilisateur);
   }



   public void deleteImage(Long userId) throws UserNotFoundException {
      Utilisateur utilisateur = utilisateurRepository.findById(userId).orElseThrow(UserNotFoundException::new);
      if(utilisateur.getImage() != null) {
         firebaseStorageService.deleteImage(utilisateur.getImage().getNom());
         utilisateur.setImage(null);
         utilisateurRepository.save(utilisateur);
      }
   }

}
