package com.example.gestionDeCovoiturage.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponse {

   private String accessToken;
   private String refreshToken;

}
