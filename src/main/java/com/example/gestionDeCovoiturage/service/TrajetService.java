package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.exceptions.invalid.ReservationRequestException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Trajet;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import com.example.gestionDeCovoiturage.utils.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrajetService {

    private final TrajetRepository trajetRepository;
    private final ReservationRepository reservationRepository;
    private final TrajetMapper trajetMapper;

    private final UtilisateurRepository utilisateurRepository;

    public  TrajetDTO create(TrajetDTO trajetDTO) {
        Trajet trajet = trajetMapper.createTrajet(trajetDTO);
        Utilisateur proposeur = utilisateurRepository.findById(Principal.getCurrentUser().getId()).orElseThrow();
        trajet.setProposeur(proposeur);
        return trajetMapper.trajetToTrajetDTO(trajetRepository.save(trajet));
    }

    public TrajetDTO getById(Long id) throws NotFoundException {
        Trajet trajet = trajetRepository.findById(id).orElseThrow(NotFoundException::new);
        return trajetMapper.trajetToTrajetDTO(trajet);
    }

    public void addReservationToTrajet(Long trajetId, Long reservationId) throws NotFoundException, ReservationRequestException {
        Trajet trajet = trajetRepository.findById(trajetId).orElseThrow(NotFoundException::new);
        if(trajet.getReservations().size() == trajet.getNbrPlacesDisponibles())
            throw new ReservationRequestException();
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(NotFoundException::new);
        trajet.getReservations().add(reservation);
    }

    public void deleteTrajet(Long id) throws NotFoundException {
        Trajet trajet = trajetRepository.findById(id).orElseThrow(NotFoundException::new);
         trajetRepository.delete(trajet);
         }


    public TrajetDTO update(TrajetDTO trajetDTO) throws NotFoundException {
        Trajet trajetToModify = trajetRepository.findById(trajetDTO.getId())
                .orElseThrow(NotFoundException::new);
        trajetMapper.updateTrajetFromDTO(trajetDTO,trajetToModify);

        return trajetMapper.trajetToTrajetDTO(trajetRepository.save(trajetToModify));
    }

    public List<TrajetDTO> findAll(int page, int size) {
        return trajetRepository.findAll(PageRequest.of(page, size))
                .getContent().stream()
                .map(trajetMapper::trajetToTrajetDTO)
                .collect(Collectors.toList());
    }

    public List<TrajetDTO> findByKeyword(int page, int size, String keyword) {
        return trajetRepository.findByVilleDepartOrVilleArrive(keyword,keyword,PageRequest.of(page, size))
                .stream().map(trajetMapper::trajetToTrajetDTO)
                .collect(Collectors.toList());
    }

    public List<TrajetDTO> getAll(int page, int size, String keyword) {
        return keyword.isEmpty() ?
                findAll(page, size) : findByKeyword(page, size, keyword);
    }
    public List<TrajetDTO> getProposes(int page, int size, String keyword) {
        return keyword.isEmpty() ?
                findAll(page, size) : findByKeyword(page, size, keyword);
    }

    public List<TrajetDTO> trajetNonRealise(){
        return trajetMapper.toTrajetDTOList(trajetRepository.findByDateDepartLessThan(new Date()));
    }


}

