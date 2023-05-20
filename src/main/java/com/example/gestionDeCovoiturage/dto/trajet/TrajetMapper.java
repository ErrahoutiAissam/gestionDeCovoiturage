package com.example.gestionDeCovoiturage.dto.trajet;

import com.example.gestionDeCovoiturage.models.Trajet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrajetMapper {

   @Mapping(target = "reservations", ignore = true)
   Trajet createTrajet(TrajetDTO trajetDTO);


   @Mapping(target = "reservations", ignore = true)
   TrajetDTO trajetToTrajetDTO(Trajet trajet);

}
