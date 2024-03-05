package com.projpba.projpba.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthResponse {
    String accessToken;
    String tokenType = "Bearer";
}
