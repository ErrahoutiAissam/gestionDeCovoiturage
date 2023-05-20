package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.dto.reservation.ReservationMapper;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationService   {

    private final ReservationRepository reservationRepo;

    private final ReservationMapper reservationMapper;


    public ReservationDTO createReservation(ReservationDTO reservationDTO){

        return reservationMapper.ResTOResDTO(reservationRepo.save(
                reservationMapper.createReservation(reservationDTO)
        ));
    }

    public void update(ReservationDTO reservationDTO) throws NotFoundException{
        Reservation reservation = reservationRepo.findById(reservationDTO.getId()).
                orElseThrow(NotFoundException::new);

        reservationMapper.updateReservationFromDTO(reservationDTO, reservation);
        reservationRepo.save(reservation);

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

    public List<ReservationDTO> getAll(){
        return reservationMapper.toResDTOList(reservationRepo.findAll());
    }






}
