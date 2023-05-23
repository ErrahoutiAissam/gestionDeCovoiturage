package com.example.gestionDeCovoiturage.dto.reservation;

import com.example.gestionDeCovoiturage.dto.user.UserMapper;
import com.example.gestionDeCovoiturage.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ReservationMapper {




    @Mapping(target = "utilisateur" , ignore = true)
    @Mapping(target = "trajet", ignore = true)
    @Mapping(target = "etat", defaultValue = "EN_ATTENTE")
    ReservationDTO ResTOResDTO(Reservation reservation);

    @Mapping(target = "utilisateur" , ignore = true)
    @Mapping(target = "trajet", ignore = true)
    @Mapping(target = "etat", defaultValue = "EN_ATTENTE")
    Reservation createReservation(ReservationDTO reservationDTO);

    @Mapping(target = "utilisateur" , ignore = true)
    @Mapping(target = "trajet", ignore = true)
    @Mapping(target = "etat", defaultValue = "EN_ATTENTE")
    List<ReservationDTO> toResDTOList(List<Reservation> reservationList);

    @Mapping(target = "etat", defaultValue = "EN_ATTENTE")
    void updateReservationFromDTO(ReservationDTO reservationDTO,
                                  @MappingTarget Reservation reservation);


}
