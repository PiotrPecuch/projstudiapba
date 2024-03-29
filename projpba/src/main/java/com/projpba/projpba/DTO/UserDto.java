package com.projpba.projpba.DTO;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userEmail;
    private String password;
    private String firstName;
    private String lastName;
    private String userPhoneNumber;
    private Date birthDate;

}
