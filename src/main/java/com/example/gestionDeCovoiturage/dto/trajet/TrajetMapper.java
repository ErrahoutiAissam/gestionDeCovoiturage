package com.example.gestionDeCovoiturage.dto.trajet;

import com.example.gestionDeCovoiturage.dto.reservation.ReservationDTO;
import com.example.gestionDeCovoiturage.models.Reservation;
import com.example.gestionDeCovoiturage.models.Trajet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TrajetMapper {

   @Mapping(target = "reservations", ignore = true)
   Trajet createTrajet(TrajetDTO trajetDTO);


   @Mapping(target = "reservations", ignore = true)
   TrajetDTO trajetToTrajetDTO(Trajet trajet);

   void updateTrajetFromDTO(TrajetDTO trajetDTO, @MappingTarget Trajet trajet);

   List<TrajetDTO> toTrajetDTOList(List<Trajet> trajetList);

}
