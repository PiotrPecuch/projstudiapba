package com.projpba.projpba.Controller;

import com.projpba.projpba.DTO.JWTAuthResponse;
import com.projpba.projpba.DTO.LoginDto;
import com.projpba.projpba.DTO.UserDto;
import com.projpba.projpba.Mapper.UserDtoToUser;
import com.projpba.projpba.Repository.UserRepository;
import com.projpba.projpba.Security.JWT.JwtTokenProvider;
import com.projpba.projpba.Repository.AuthService;
import com.projpba.projpba.Services.JwtDecoder;
import com.projpba.projpba.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    private final UserRepository userRepository;

    private AuthService authService;

    private JwtDecoder jwtDecoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        System.out.println("Token from authService: " + token);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }



    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto) {
        userRepository.save(UserDtoToUser.toUser(userDto));
        return userDto;
    }
}