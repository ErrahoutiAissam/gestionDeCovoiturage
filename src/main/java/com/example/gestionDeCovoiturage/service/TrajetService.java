package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.dto.trajet.TrajetDTO;
import com.example.gestionDeCovoiturage.exceptions.alreadyExists.TrajetAlreadyExists;
import com.example.gestionDeCovoiturage.models.Trajet;
import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrajetService {

    private final TrajetRepository trajetRepo;
//
//    public  TrajetDTO create(TrajetDTO trajetDTO) throws TrajetAlreadyExists {
//        Optional<Trajet> optionalTrajet= trajetRepo.findById(trajetDTO.getId());
//        if(optionalTrajet.isPresent()) throw new TrajetAlreadyExists();
//        trajetRepo.save(trajetDTO);
//       return trajetDTO  ;
//    }
//
//    public TrajetDTO getById(Long id) throws  {
//        if(trajetRepo.findById(id).isEmpty())
//            throw new NotFoundException();
//        TrajetDTO trajetDTO = new TrajetDTO();
//        trajetDTO.setId(trajetRepo.findById(id).get().getId());
//        trajetDTO.setDateDepart(trajetRepo.findById(id).get().getDateDepart());
//            trajetDTO.setId(trajetRepo.findById(id).get().getId());
//            trajetDTO.setVilleArrive(trajetRepo.findById(id).get().getVilleArrive());
//            trajetDTO.setVilleDepart(trajetRepo.findById(id).get().getVilleDepart());
//            trajetDTO.setNbrPlaceDisponible(trajetRepo.findById(id).get().getNbrPlaceDisponible());
//            trajetDTO.setPrixParPersonne(trajetRepo.findById(id).get().getPrixParPersonne());
//          //  trajetDTO.setReservations();
//
//        return trajetDTO;
//    }
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
