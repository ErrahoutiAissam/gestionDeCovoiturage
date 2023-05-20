package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.TrajetAlreadyExists;
import com.example.gestionDeCovoiturage.exceptions.invalid.ReservationRequestException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Trajet;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrajetService {

    private final TrajetRepository trajetRepository;
    private final ReservationRepository reservationRepository;
    private final TrajetMapper trajetMapper;

    public  TrajetDTO create(TrajetDTO trajetDTO) {
        Trajet trajet = trajetMapper.createTrajet(trajetDTO);
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
    public void addReservationsToTrajet(Long trajetId, List<Long> reservationIds) throws NotFoundException, ReservationRequestException {
        for (Long id : reservationIds) {
            addReservationToTrajet(trajetId, id);
        }
    }





//    public void deleteTrajet(Long id) throws NotFoundException {
//        Optional<Trajet> trajetOptional=trajetRepo.findById(id);
//        if(trajetOptional.isEmpty()){
//            throw new NotFoundException();
//        }
//        trajetRepo.delete(trajetOptional.get());
//    }
//
//    public void update(TrajetDTO trajetDTO) throws NotFoundException {
//        Trajet trajet = trajetRepo.findById(trajetDTO.getId())
//                .orElseThrow(NotFoundException::new);
//        TrajetDTO trajetDTO1 = new TrajetDTO();
//        trajetDTO1.setId(trajet.getId());
//        trajetDTO1.setVilleArrive(trajet.getVilleArrive());
//        trajetDTO1.setVilleDepart(trajet.getVilleDepart());
//        trajetDTO1.setNbrPlaceDisponible(trajet.getNbrPlaceDisponible());
//        trajetDTO1.setPrixParPersonne(trajet.getPrixParPersonne());
//        //  trajetDTO1.setReservations(trajet.getReservations());
//        trajetRepo.save(trajetDTO);
//    }
//
//
//        public List<TrajetDTO> getAll() throws NotFoundException {
//        List<Trajet> list= trajetRepo.findAll();
//        if(list.isEmpty()) throw new NotFoundException();
//
//        return null;
//    }


}
