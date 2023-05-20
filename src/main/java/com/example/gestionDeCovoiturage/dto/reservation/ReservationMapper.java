package com.example.gestionDeCovoiturage.dto.reservation;

import com.example.gestionDeCovoiturage.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {


    @Mapping(target = "utilisateur" , ignore = true)
    @Mapping(target = "trajet", ignore = true)
    ReservationDTO ResTOResDTO(Reservation reservation);

    @Mapping(target = "utilisateur" , ignore = true)
    @Mapping(target = "trajet", ignore = true)
    Reservation createReservation(ReservationDTO reservationDTO);

    @Mapping(target = "utilisateur" , ignore = true)
    @Mapping(target = "trajet", ignore = true)
    List<ReservationDTO> toResDTOList(List<Reservation> reservationList);

    void updateReservationFromDTO(ReservationDTO reservationDTO,
                                  @MappingTarget Reservation reservation);


}
