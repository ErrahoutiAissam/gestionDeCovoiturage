package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.dto.trajet.TrajetMapper;
import com.example.gestionDeCovoiturage.exceptions.invalid.ReservationRequestException;
import com.example.gestionDeCovoiturage.exceptions.notfound.NotFoundException;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Trajet;
import com.example.gestionDeCovoiturage.repositories.ReservationRepository;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteTrajet(Long id) throws NotFoundException {
        Trajet trajet = trajetRepository.findById(id).orElseThrow(NotFoundException::new);
         trajetRepository.delete(trajet);
         }


    public TrajetDTO update(TrajetDTO trajetDTO, Long id) throws NotFoundException {
        Trajet trajetToModify = trajetRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        trajetMapper.updateTrajetFromDTO(trajetDTO,trajetToModify);

        return trajetMapper.trajetToTrajetDTO(trajetRepository.save(trajetToModify));
    }

        public List<TrajetDTO> getAll() {
        return  trajetMapper.toTrajetDTOList(trajetRepository.findAll());
    }


}
