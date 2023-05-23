package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationIdDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationMapper;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.dto.user.UserMapper;
import com.example.gestionDeCovoiturage.dto.user.UtilisateurDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.AlreadyExistException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservationService   {

    private final ReservationRepository reservationRepo;

    private final TrajetRepository trajetRepository;

    private final ReservationMapper reservationMapper;

    private final UtilisateurRepository userRepo;


    public ReservationDTO createReservation(ReservationDTO reservationDTO) throws AlreadyExistException {

        if(reservationRepo.existsById(reservationDTO.getId()))
            throw new AlreadyExistException();

     return reservationMapper.ResTOResDTO(reservationRepo.
             save(reservationMapper.createReservation(reservationDTO)));
    }

    public ReservationDTO update(ReservationIdDTO res, Long id) throws NotFoundException{
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(NotFoundException::new);

        if(res.getIdTrajet() != null  && res.getIdUser() != null) {
            Trajet trajet = trajetRepository.findById(res.getIdTrajet()).orElseThrow(NotFoundException::new);
            Utilisateur user = userRepo.findById(res.getIdUser()).orElseThrow(NotFoundException::new);

            reservation.setTrajet(trajet);
            reservation.setUtilisateur(user);
            reservation.setEtat(res.getEtat());
        }


        return reservationMapper.ResTOResDTO(reservationRepo.save(reservation));

    }

    public void delete(Long id) throws NotFoundException{
        Reservation reservation = reservationRepo.findById(id).
                orElseThrow(NotFoundException::new);

        reservationRepo.delete(reservation);
    }

    public ReservationDTO getById(Long id) throws NotFoundException {

        return reservationMapper.ResTOResDTO(reservationRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }
    public Reservation getById2(Long id) throws NotFoundException {
        return reservationRepo.findById(id).orElseThrow(NotFoundException::new);

    }

    public List<ReservationDTO> findAll(int page, int size) {
        return reservationRepo.findAll(PageRequest.of(page, size))
                .getContent().stream()
                .map(reservationMapper::ResTOResDTO)
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> findByKeyword(int page, int size, String keyword) {
        return reservationRepo.findByEtat(EtatReservation.valueOf(keyword),PageRequest.of(page, size))
                .stream().map(reservationMapper::ResTOResDTO)
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> getAll(int page, int size, String keyword) {
        return keyword.isEmpty() ?
                findAll(page, size) : findByKeyword(page, size, keyword);
    }

    public void confirmeState(Long id, Trajet trajet) throws NotFoundException{

        Reservation reservation = reservationRepo.findById(id).
                orElseThrow(NotFoundException::new);

        reservation.setEtat(EtatReservation.CONFIRME);

        List<Reservation> reservationList = trajet.getReservations();
        if(reservationList.contains(reservation)){
            trajet.setNbrPlacesDisponibles(trajet.getNbrPlacesDisponibles() + 1);
        }

        trajetRepository.save(trajet);

    }

    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    public ReservationDTO createFromIdTrajet(ReservationIdDTO reservationIdDTO) throws AlreadyExistException, NotFoundException {


            Reservation reservation = new Reservation();
            if(reservationIdDTO.getIdTrajet() != null ) {
                Trajet trajet = trajetRepository.findById(reservationIdDTO.getIdTrajet()).orElseThrow(NotFoundException::new);
                reservation.setTrajet(trajet);
            }
            Utilisateur user = Principal.getCurrentUser();
            reservation.setUtilisateur(user);

            reservation.setEtat(EtatReservation.EN_ATTENTE);


        return reservationMapper.ResTOResDTO(reservationRepo.save(reservation));

    }



    public ReservationDTO createFromIds(ReservationIdDTO reservationIdDTO) throws NotFoundException {

        Reservation reservation = new Reservation();

        if(reservationIdDTO.getIdTrajet() != null && reservationIdDTO.getIdUser() != null ) {
            Trajet trajet = trajetRepository.findById(reservationIdDTO.getIdTrajet()).orElseThrow(NotFoundException::new);
            Utilisateur user = userRepo.findById(reservationIdDTO.getIdUser()).orElseThrow(NotFoundException::new);

            reservation.setTrajet(trajet);
            reservation.setUtilisateur(user);
            reservation.setEtat(reservationIdDTO.getEtat());

        }

        return reservationMapper.ResTOResDTO(reservationRepo.save(reservation));

    }

    public List<Reservation> getReservationOfCurrenUser(Long id){
        return reservationRepo.findByUtilisateurId(id);
    }













}
