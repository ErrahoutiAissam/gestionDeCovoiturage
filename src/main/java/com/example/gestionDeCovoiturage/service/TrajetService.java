package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationMapper;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.dto.user.UserMapper;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.exceptions.invalid.ReservationRequestException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.EtatReservation;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Trajet;
import com.example.gestionDeCovoiturage.models.Utilisateur;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import com.example.gestionDeCovoiturage.repositories.UtilisateurRepository;
import com.example.gestionDeCovoiturage.utils.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrajetService {

    private final TrajetRepository trajetRepository;
    private final ReservationRepository reservationRepository;
    private final TrajetMapper trajetMapper;

    private final UserMapper userMapper;

    private final ReservationMapper reservationMapper;

    private final UtilisateurRepository utilisateurRepository;

    public  TrajetDTO create(TrajetDTO trajetDTO) {
        Trajet trajet = trajetMapper.createTrajet(trajetDTO);
        Utilisateur proposeur = utilisateurRepository.findById(Principal.getCurrentUser().getId()).orElseThrow();
        trajet.setProposeur(proposeur);
        return trajetMapper.trajetToTrajetDTO(trajetRepository.save(trajet));
    }

    public TrajetDTO getById(Long id) throws NotFoundException {
        Trajet trajet = trajetRepository.findById(id).orElseThrow(NotFoundException::new);
        TrajetDTO trajetDTO = trajetMapper.trajetToTrajetDTO(trajet);
        trajetDTO.setProposeur(userMapper.toUtilisateurResponseDTO(trajet.getProposeur()));
        trajetDTO.setReservations(trajet.getReservations().stream().map(
                res -> {
                    ReservationDTO resDTO = new ReservationDTO();
                    resDTO.setEtat(res.getEtat());
                    resDTO.setUtilisateur(userMapper.toUtilisateurResponseDTO(res.getUtilisateur()));
                    return resDTO;
                }
        ).collect(Collectors.toList()));
        return trajetDTO;
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

    public List<TrajetDTO> findByKeyword(int page, int size, String keyword) {
        return trajetRepository.findByVilleDepartOrVilleArrive(keyword,keyword,PageRequest.of(page, size))
                .stream().map(trajetMapper::trajetToTrajetDTO)
                .collect(Collectors.toList());
    }
    /**getting trajets all*/

    public List<TrajetDTO> getAll(int page, int size, String keyword) {
        return keyword.isEmpty() ?
                findAll(page, size) : findByKeyword(page, size, keyword);
    }

    public List<TrajetDTO> findAll(int page, int size) {
        return trajetRepository.findAll(PageRequest.of(page, size))
                .getContent().stream()
                .map(trajetMapper::trajetToTrajetDTO)
                .collect(Collectors.toList());
    }

    /**getting trajets proposes*/
    public List<TrajetDTO> getProposes(int page, int size, String keyword) {
        return keyword.isEmpty() ?
                findPropose(page, size) : findByKeyword(page, size, keyword);
    }
    public List<TrajetDTO> findPropose( int page, int size) {
       Utilisateur utilisateur= Objects.requireNonNull(Principal.getCurrentUser());
        Page<Trajet> trajetPage = trajetRepository.findAllByProposeur( utilisateur,PageRequest.of(page, size));
        return trajetPage.getContent().stream()
                .map(trajetMapper::trajetToTrajetDTO)
                .collect(Collectors.toList());
    }
    /**getting trajets historique*/

    public List<TrajetDTO> getHistorique(int page, int size, String keyword) {
        return keyword.isEmpty() ?
                findHistorique(page, size) : findByKeyword(page, size, keyword);
    }
    public List<TrajetDTO> findHistorique( int page, int size) {
        Utilisateur utilisateur= Objects.requireNonNull(Principal.getCurrentUser());
        Page<Trajet> trajetPage = trajetRepository.findAllByProposeurAndDateDepartLessThan( utilisateur,new Date(),PageRequest.of(page, size));
        return trajetPage.getContent().stream()
                .map(trajetMapper::trajetToTrajetDTO)
                .collect(Collectors.toList());
    }

    public List<UtilisateurDTO> getRestUsers(Long trajetId) throws NotFoundException {
        Trajet trajet = trajetRepository.findById(trajetId).orElseThrow(NotFoundException::new);
        if(trajet.getReservations().isEmpty()){
            return utilisateurRepository.findAll()
                    .stream().map(userMapper::toUtilisateurResponseDTO)
                    .collect(Collectors.toList());
        }

        List<Utilisateur> restUsers = utilisateurRepository.findByIdNotIn(
                trajet.getReservations().stream().map(
                        res -> res.getUtilisateur().getId()
                ).collect(Collectors.toList())
        );

        return restUsers.stream().map(userMapper::toUtilisateurResponseDTO)
                .collect(Collectors.toList());
    }


    public void addReservationsToTrajet(Long trajetId, List<Long> ids) throws NotFoundException {
        Trajet trajet = trajetRepository.findById(trajetId).orElseThrow(NotFoundException::new);
        for(Long id : ids) {
            Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow();
            Reservation reservation = new Reservation();
            reservation.setUtilisateur(utilisateur);
            reservation.setTrajet(trajet);
            reservation.setEtat(EtatReservation.EN_ATTENTE);
            trajet.getReservations().add(reservationRepository.save(reservation));
        }
        trajetRepository.save(trajet);
    }

    public void removeReservationFromTrajet(Long trajetId, Long resId) throws NotFoundException {
        Trajet trajet = trajetRepository.findById(trajetId).orElseThrow(NotFoundException::new);
        Reservation reservation = reservationRepository.findById(resId).orElseThrow(NotFoundException::new);
        if(reservation.getEtat().equals("CONFIRME")) {
            trajet.setNbrPlacesDisponibles(trajet.getNbrPlacesDisponibles() + 1);
        }
        trajet.getReservations().remove(reservation);
        trajetRepository.save(trajet);
    }


    public boolean confirmState(Long trajetId, Long resId) throws NotFoundException {
        Trajet trajet = trajetRepository.findById(trajetId).orElseThrow(NotFoundException::new);
        Reservation reservation = reservationRepository.findById(resId).orElseThrow(NotFoundException::new);
        int nbrDisp = trajet.getNbrPlacesDisponibles();
        if(nbrDisp == 0) return false;
        reservation.setEtat(EtatReservation.CONFIRME);
        trajet.setNbrPlacesDisponibles(nbrDisp  - 1);
        trajetRepository.save(trajet);
        return true;
    }



}

