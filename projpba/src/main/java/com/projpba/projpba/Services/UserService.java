package com.projpba.projpba.Services;

import com.projpba.projpba.Entity.User;
import com.projpba.projpba.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String getNameFromEmail(String email){
        return userRepository.findNameByEmail(email);
    }

    public void updateResetPasswordToken(String token, String email)  {
        Optional<User> userOptional = Optional.of(userRepository.findByUserEmail(email).get());
        if(userOptional != null){
            User user = userOptional.get();
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }else {
              throw new UsernameNotFoundException(email);
        }
    }

    public User getByResetPasswordToken(String token){
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
}
