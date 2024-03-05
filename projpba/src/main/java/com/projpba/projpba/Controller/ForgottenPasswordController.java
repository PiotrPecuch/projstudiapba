package com.projpba.projpba.Controller;


import com.projpba.projpba.Entity.User;
import com.projpba.projpba.Services.EmailSenderService;
import com.projpba.projpba.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/forgot_password")
@CrossOrigin(origins = "http://localhost:3000")
public class ForgottenPasswordController {

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    UserService userService;

    @PostMapping("/process")
    public void processForgotPassword(@RequestParam String email){
        System.out.println("process");
        String token = generateRandomString(50);
        try{
            userService.updateResetPasswordToken(token,email);
            String resetPasswordLink = "http://localhost:3000/reset_password?token="+token;
            emailSenderService.sendEmail(email,resetPasswordLink);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/reset_password")
    public void processResetPassword(@RequestParam(name = "token") String token, @RequestParam(name = "password") String password) {
        User user = userService.getByResetPasswordToken(token);
        try {
            userService.updatePassword(user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('a' + Math.random() * ('z' - 'a' + 1));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

}
