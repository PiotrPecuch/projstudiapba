package com.projpba.projpba.Mapper;

import com.projpba.projpba.DTO.UserDto;
import com.projpba.projpba.Entity.Role;
import com.projpba.projpba.Entity.User;
import com.projpba.projpba.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserDtoToUser {


    private static RoleRepository roleRepository = null;

    @Autowired
    public UserDtoToUser(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static User toUser(UserDto userDto) {
        Optional<Role> userRole = roleRepository.findByRoleName("USER");

        if (userRole.isPresent()) {
            Set<Role> roles = new HashSet<>();
            roles.add(userRole.get());

            User user = User.builder()
                    .userEmail(userDto.getUserEmail())
                    .birthDate(userDto.getBirthDate())
                    .userPhoneNumber(userDto.getUserPhoneNumber())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .roles(roles)
                    .password(userDto.getPassword())
                    .build();

            return user;
        } else {
            throw new RuntimeException("Rola ROLE_USER nie została znaleziona");
        }
    }

}
